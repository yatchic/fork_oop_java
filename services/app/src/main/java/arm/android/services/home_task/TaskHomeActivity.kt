package arm.android.services.home_task

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.os.Messenger
import android.os.RemoteException
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import arm.android.services.R

class TaskHomeActivity : AppCompatActivity() {
    private var isBound = false
    private lateinit var serviceMessenger: Messenger
    private lateinit var clientMessenger: Messenger
    private val handler = Handler(Looper.getMainLooper()) {
        when (it.what) {
            MessengerService.MSG_UPDATE_PROGRESS -> {
                findViewById<TextView>(R.id.progressText).text = it.arg1.toString()
                true
            }
            else -> false
        }
    }

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            serviceMessenger = Messenger(binder)
            clientMessenger = Messenger(handler)
            isBound = true
            startProgressUpdates()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_home)

        // Привязка сервиса
        bindService(
            Intent(this, MessengerService::class.java),
            connection,
            Context.BIND_AUTO_CREATE
        )
    }

    private fun startProgressUpdates() {
        val msg = Message.obtain(null, MessengerService.MSG_GET_PROGRESS)
        msg.replyTo = clientMessenger
        try {
            serviceMessenger.send(msg)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isBound) unbindService(connection)
    }
}