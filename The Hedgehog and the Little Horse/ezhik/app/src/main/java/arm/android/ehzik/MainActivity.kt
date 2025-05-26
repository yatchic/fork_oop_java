package arm.android.ehzik

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
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
                Log.d("error1","$replyCount")
                countView.text = "от лошадки: $replyCount"

                YozhikReceiver().onReceive(c, intent)
            }
        }
        registerReceiver(receiver, IntentFilter("Yozhik!"))


        Handler(Looper.getMainLooper()).postDelayed({
            Intent("Loshadka!").apply {
                setPackage("arm.android.loshadka")
            }.also { sendBroadcast(it, "arm.android.ehzik.HEAR_BROADCAST") }
        }, 1000)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}
