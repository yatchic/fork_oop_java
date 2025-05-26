package arm.android.loshadka

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import kotlinx.coroutines.*

class LoshadkaReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        GlobalScope.launch(Dispatchers.IO) {
            delay(5000)
            Intent("Yozhik!").apply {
                setPackage("arm.android.ehzik")
            }.also { bc ->
                context.sendBroadcast(bc, "arm.android.ehzik.HEAR_BROADCAST")
            }
        }
    }
}
