package arm.android.services.task1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import arm.android.services.R

class TaskOneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_one)

        val startServiceButton = findViewById<Button>(R.id.startServiceButton)
        startServiceButton.setOnClickListener {
            Log.d(TAG, "запуск сервиса...")
            val serviceIntent = Intent(this, MyService::class.java)
            startService(serviceIntent)
        }
    }

    companion object {
        private const val TAG = "Task1Activity"
    }
}