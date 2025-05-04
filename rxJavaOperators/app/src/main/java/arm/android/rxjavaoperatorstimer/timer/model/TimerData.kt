package arm.android.rxjavaoperatorstimer.timer.model

data class TimerData(
    val id: Int,
    var elapsedSeconds: Long = 0,
    var isRunning: Boolean = false,
    var showSeconds: Boolean = true
)