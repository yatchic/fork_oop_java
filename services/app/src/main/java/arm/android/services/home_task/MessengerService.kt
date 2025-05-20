package arm.android.services.home_task

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.os.Messenger
import android.os.RemoteException

class MessengerService : Service() {
    private lateinit var messenger: Messenger
    private var progress = 10
    private val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MSG_GET_PROGRESS -> {
                    val clientMessenger = msg.replyTo
                    val response = Message.obtain(null, MSG_UPDATE_PROGRESS, progress, 0)
                    try {
                        clientMessenger.send(response)
                    } catch (e: RemoteException) {
                        e.printStackTrace()
                    }
                    if (progress > 0) progress--
                }
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        messenger = Messenger(handler)
    }

    override fun onBind(intent: Intent): IBinder {
        return messenger.binder
    }

    companion object {
        const val MSG_GET_PROGRESS = 1
        const val MSG_UPDATE_PROGRESS = 2
    }
}