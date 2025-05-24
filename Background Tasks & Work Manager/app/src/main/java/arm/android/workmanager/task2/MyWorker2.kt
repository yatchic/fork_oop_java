package arm.android.workmanager.task2

import android.content.Context
import android.widget.Toast
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MyWorker2(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        withContext(Dispatchers.Main) {
            Toast.makeText(
                applicationContext,
                "запущен процесс",
                Toast.LENGTH_SHORT
            ).show()
        }

        delay(10000L)

        withContext(Dispatchers.Main) {
            Toast.makeText(
                applicationContext,
                "выполнен процесс",
                Toast.LENGTH_SHORT
            ).show()
        }

        return Result.success()
    }
}