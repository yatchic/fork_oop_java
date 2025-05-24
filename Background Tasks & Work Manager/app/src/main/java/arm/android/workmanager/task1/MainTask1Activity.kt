package arm.android.workmanager.task1

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import arm.android.workmanager.R

class MainTask1Activity : AppCompatActivity() {
    private lateinit var startWorkButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_task1)

        startWorkButton = findViewById(R.id.startWorkButton3)

        startWorkButton.setOnClickListener {
            val workRequest = OneTimeWorkRequest.Builder(MyWorker1::class.java).build()
            WorkManager.getInstance(this).enqueue(workRequest)
        }
    }
}