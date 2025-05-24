package arm.android.workmanager.task3

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import arm.android.workmanager.R
import java.util.concurrent.TimeUnit

class MainTask3Activity : AppCompatActivity() {
    private lateinit var startWorkButton: Button
    private lateinit var cancelWorkButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_task3)

        startWorkButton = findViewById(R.id.startWorkButton3)
        cancelWorkButton = findViewById(R.id.cancelWorkButton)


        startWorkButton.setOnClickListener {
            val workRequest = PeriodicWorkRequestBuilder<MyWorker3>(
                15, TimeUnit.MINUTES,
                9, TimeUnit.MINUTES
            ).build()

            WorkManager.getInstance(this).enqueueUniquePeriodicWork(
                WORK_ID,
                ExistingPeriodicWorkPolicy.KEEP,
                workRequest
            )
        }


        cancelWorkButton.setOnClickListener {
            WorkManager.getInstance(this).cancelUniqueWork(WORK_ID)
        }
    }

    companion object {
        private const val WORK_ID = "уникальный идентификатор"
    }
}