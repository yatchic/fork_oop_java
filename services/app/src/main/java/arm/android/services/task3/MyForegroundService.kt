package arm.android.services.task3

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import androidx.core.app.NotificationCompat
import arm.android.services.R

class MyForegroundService : Service() {
    private var progress = 20
    private val binder = LocalBinder()
    private val notificationId = 101
    private lateinit var notificationManager: NotificationManager

    inner class LocalBinder : Binder() {
        fun getService() = this@MyForegroundService
    }

    override fun onCreate() {
        super.onCreate()
        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel()
        startForeground(notificationId, buildNotification("Service running"))
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startCountdown()
        return START_NOT_STICKY
    }

    private fun startCountdown() {
        Handler(Looper.getMainLooper()).postDelayed({
            stopSelf()
        }, 20000)
    }

    fun getProgress(): Int {
        if (progress > 0) progress--
        updateNotification()
        return progress
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "фоновое подключение",
                "фоновый сервис",
                NotificationManager.IMPORTANCE_LOW
            )
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun buildNotification(text: String): Notification {
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            Intent(this, TaskThreeActivity::class.java),
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        return NotificationCompat.Builder(this, "фоновое подключение")
            .setContentTitle("отсчетный сервис")
            .setContentText(text)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentIntent(pendingIntent)
            .build()
    }

    private fun updateNotification() {
        notificationManager.notify(
            notificationId,
            buildNotification("прогресс: $progress")
        )
    }

    override fun onBind(intent: Intent): IBinder = binder

    override fun onDestroy() {
        super.onDestroy()
        notificationManager.cancel(notificationId)
    }
}