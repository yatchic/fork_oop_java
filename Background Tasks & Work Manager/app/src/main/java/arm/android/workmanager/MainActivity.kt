package arm.android.workmanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import arm.android.workmanager.homework.MainHomeWorkActivity
import arm.android.workmanager.task1.MainTask1Activity
import arm.android.workmanager.task2.MainTask2Activity
import arm.android.workmanager.task3.MainTask3Activity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val task1: Button =findViewById(R.id.task1)
        val task2: Button =findViewById(R.id.task2)
        val task3: Button =findViewById(R.id.task3)
        val taskHome: Button =findViewById(R.id.taskHome)

        task1.setOnClickListener{
            switchActivity(this, MainTask1Activity::class.java)
        }
        task2.setOnClickListener{
            switchActivity(this, MainTask2Activity::class.java)
        }

    task3.setOnClickListener{
        switchActivity(this, MainTask3Activity::class.java)
    }

taskHome.setOnClickListener{
    switchActivity(this, MainHomeWorkActivity::class.java)
}

    }

    fun switchActivity(activity: AppCompatActivity, YourSecondActivity: Class<*>) {
        val intent = Intent(activity, YourSecondActivity)
        activity.startActivity(intent)
    }
}