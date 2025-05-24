package arm.android.workmanager.task2

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import arm.android.workmanager.R

class MainTask2Activity : AppCompatActivity() {
    private lateinit var startWorkButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_task2)

        startWorkButton = findViewById(R.id.startWorkButton2)

        startWorkButton.setOnClickListener {
            // Создаем ограничение: требуется подключение к сети
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED) // Исправлено на CONNECTED
                .build()

            // Создаем WorkRequest с ограничениями
            val workRequest = OneTimeWorkRequest.Builder(MyWorker2::class.java)
                .setConstraints(constraints)
                .build()

            WorkManager.getInstance(this).enqueue(workRequest)
        }
    }
}