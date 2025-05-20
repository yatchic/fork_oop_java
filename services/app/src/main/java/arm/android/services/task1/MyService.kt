package arm.android.services.task1

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log

class MyService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "сервис создан")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "сервис запущен")


        Handler(Looper.getMainLooper()).postDelayed({
            stopSelf()
        }, 3000)

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "сервис удален")
    }

    override fun onBind(intent: Intent): IBinder? = null

    companion object {
        private const val TAG = "error1"
    }
}