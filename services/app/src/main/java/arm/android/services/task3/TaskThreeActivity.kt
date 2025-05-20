package arm.android.services.task3

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import arm.android.services.R

class TaskThreeActivity : AppCompatActivity() {
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (!isGranted) Toast.makeText(this, "предупреждения отключены", Toast.LENGTH_SHORT).show()
    }

    private var isBound = false
    private lateinit var myService: MyForegroundService
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MyForegroundService.LocalBinder
            myService = binder.getService()
            isBound = true
            startProgressUpdates()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_three)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }

        findViewById<Button>(R.id.startServiceButton).setOnClickListener {
            val intent = Intent(this, MyForegroundService::class.java)
            startService(intent)
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    private fun startProgressUpdates() {
        val handler = Handler(Looper.getMainLooper())
        handler.post(object : Runnable {
            override fun run() {
                if (isBound) {
                    val progress = myService.getProgress()
                    findViewById<TextView>(R.id.countdownText).text = progress.toString()
                    if (progress > 0) {
                        handler.postDelayed(this, 1000)
                    }
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isBound) {
            unbindService(connection)
        }
    }
}