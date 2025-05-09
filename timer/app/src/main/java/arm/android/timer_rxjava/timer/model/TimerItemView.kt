package arm.android.timer_rxjava.timer.model

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.Gravity
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import arm.android.timer_rxjava.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

class TimerItemView @Inject constructor(
    private val activity: AppCompatActivity,
    private val binding: ActivityMainBinding
) {

    private var selectedTimerLayout: LinearLayout? = null
    private val timerEventBus = PublishSubject.create<TimerEvent>()
    private val timers = mutableMapOf<LinearLayout, Timer>()
    private val disposables = CompositeDisposable()
    fun resizeLayoutByScreenSize(activity: AppCompatActivity, linearLayout: LinearLayout) {
        val displayMetrics = DisplayMetrics()
        val windowManager = activity.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        val screenWidthPx = displayMetrics.widthPixels
        val screenHeightPx = displayMetrics.heightPixels

        val layoutParams = LinearLayout.LayoutParams(screenWidthPx, screenHeightPx)
        linearLayout.layoutParams = layoutParams
    }
    init {

        resizeLayoutByScreenSize(activity,binding.ln0)


        disposables.add(
            timerEventBus
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { event ->
                    val timer = timers[event.row] ?: return@subscribe
                    val timeView = event.row.getChildAt(3) as TextView

                    when (event) {
                        is TimerEvent.Start -> timer.start()
                        is TimerEvent.Pause -> timer.pause()
                        is TimerEvent.Reset -> timer.reset()
                    }

                    val disposable = timer.timeObservable()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { timeMs ->
                            val seconds = (timeMs / 1000) % 60
                            val minutes = (timeMs / 1000) / 60
                            timeView.text = String.format("%02d:%02d", minutes, seconds)
                        }

                    disposables.add(disposable)
                }
        )








        binding.addTimer.setOnClickListener {
            addNewTimerRow()
        }

        binding.delTimer.setOnClickListener {
            selectedTimerLayout?.let { row ->
                timers[row]?.clear()
                timers.remove(row)
                binding.ln2.removeView(row)
                selectedTimerLayout = null
            }
        }
    }

    private fun addNewTimerRow() {
        val heightPx = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 60f, activity.resources.displayMetrics
        ).toInt()

        val row = LinearLayout(activity).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, heightPx
            ).also {
                it.topMargin = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 8f, activity.resources.displayMetrics
                ).toInt()
            }
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            setBackgroundResource(android.R.drawable.dialog_holo_light_frame)
            setOnClickListener {
                selectedTimerLayout?.setBackgroundResource(android.R.drawable.dialog_holo_light_frame)
                selectedTimerLayout = this
                setBackgroundResource(android.R.drawable.dialog_holo_dark_frame)
            }
        }

        val timer = Timer()
        timers[row] = timer

        val startBtn = Button(activity).apply {
            text = "Старт"
            layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
            setOnClickListener { timerEventBus.onNext(TimerEvent.Start(row)) }
        }

        val pauseBtn = Button(activity).apply {
            text = "Пауза"
            layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
            setOnClickListener { timerEventBus.onNext(TimerEvent.Pause(row)) }
        }

        val resetBtn = Button(activity).apply {
            text = "Сброс"
            layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
            setOnClickListener { timerEventBus.onNext(TimerEvent.Reset(row)) }
        }

        val timeView = TextView(activity).apply {
            text = "00:00"
            layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
            gravity = Gravity.CENTER
        }

        with(row) {
            addView(startBtn)
            addView(pauseBtn)
            addView(resetBtn)
            addView(timeView)
        }

        binding.ln2.addView(row)
    }


    fun clearAll() {
        disposables.clear()
        timers.values.forEach { it.clear() }
    }

    sealed class TimerEvent(val row: LinearLayout) {
        class Start(row: LinearLayout) : TimerEvent(row)
        class Pause(row: LinearLayout) : TimerEvent(row)
        class Reset(row: LinearLayout) : TimerEvent(row)
    }
}
