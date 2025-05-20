package arm.android.services

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import arm.android.services.task1.TaskOneActivity
import arm.android.services.task2.TaskTwoActivity
import arm.android.services.home_task.TaskHomeActivity
import arm.android.services.task3.TaskThreeActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.task3Button).setOnClickListener {
            startActivity(Intent(this, TaskThreeActivity::class.java))
        }
        findViewById<Button>(R.id.taskHomeButton).setOnClickListener {
            startActivity(Intent(this, TaskHomeActivity::class.java))
        }

        val task1Button = findViewById<Button>(R.id.task1Button)
        task1Button.setOnClickListener {
            val intent = Intent(this, TaskOneActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.task2Button).setOnClickListener {
            startActivity(Intent(this, TaskTwoActivity::class.java))
        }
    }
}