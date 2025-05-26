package arm.android.ehzik

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import kotlinx.coroutines.*

class YozhikReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        GlobalScope.launch(Dispatchers.IO) {
            delay(5000)
            Intent("Loshadka!").apply {
                setPackage("arm.android.loshadka")
            }.also { bc ->
                context.sendBroadcast(bc, "arm.android.ehzik.HEAR_BROADCAST")
            }
        }
    }
}
