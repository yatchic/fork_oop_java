package arm.android.services.task2

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log

class MyBoundService : Service() {
    private var progress = 10
    private val binder = LocalBinder()

    inner class LocalBinder : Binder() {
        fun getService(): MyBoundService = this@MyBoundService
    }

    override fun onBind(intent: Intent): IBinder = binder

    fun getProgress(): Int {
        if (progress > 0) {
            progress--
            Handler(Looper.getMainLooper()).postDelayed({ stopSelf() }, 10000)
        }
        return progress
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyBoundService", "сервис удален")
    }
}