package arm.android.services.task2

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import arm.android.services.R

class TaskTwoActivity : AppCompatActivity() {

    private var isBound = false
    private lateinit var myService: MyBoundService
    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MyBoundService.LocalBinder
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
        setContentView(R.layout.activity_task_two)


        findViewById<Button>(R.id.startServiceButton).setOnClickListener {
            Intent(this, MyBoundService::class.java).also { intent ->
                bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
            }
        }
    }

    private fun startProgressUpdates() {
        val handler = Handler(Looper.getMainLooper())
        handler.post(object : Runnable {
            override fun run() {
                if (isBound) {
                    val progress = myService.getProgress()
                    findViewById<TextView>(R.id.progressText).text = progress.toString()
                    if (progress > 0) handler.postDelayed(this, 1000)
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isBound) {
            unbindService(serviceConnection)
        }
    }
}