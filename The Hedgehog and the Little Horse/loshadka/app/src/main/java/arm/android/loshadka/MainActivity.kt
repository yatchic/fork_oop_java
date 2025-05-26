package arm.android.loshadka

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var countView: TextView
    private var replyCount = 0
    private lateinit var receiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        countView = findViewById(R.id.countView)

        receiver = object : BroadcastReceiver() {
            override fun onReceive(c: Context, intent: Intent) {
                replyCount++
                countView.text = "от ежика: $replyCount"

                LoshadkaReceiver().onReceive(c, intent)
            }
        }
        registerReceiver(receiver, IntentFilter("Loshadka!"))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}
