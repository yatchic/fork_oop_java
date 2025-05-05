package arm.android.timer_rxjava.timer.model

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class Timer @Inject constructor() {

    private var startTime: Long = 0
    private var elapsedTime: Long = 0
    private var isRunning = false

    private val timeSubject: BehaviorSubject<Long> = BehaviorSubject.createDefault(0L)
    private var ticker = Observable.interval(1, TimeUnit.SECONDS)

    private var disposable = io.reactivex.rxjava3.disposables.Disposable.disposed()

    fun timeObservable(): Observable<Long> = timeSubject.hide()

    fun start() {
        if (!isRunning) {
            startTime = System.currentTimeMillis() - elapsedTime
            isRunning = true

            disposable = ticker.subscribe {
                val currentTime = System.currentTimeMillis() - startTime
                timeSubject.onNext(currentTime)
            }
        }
    }

    fun pause() {
        if (isRunning) {
            elapsedTime = System.currentTimeMillis() - startTime
            isRunning = false
            disposable.dispose()
        }
    }

    fun reset() {
        startTime = 0
        elapsedTime = 0
        isRunning = false
        disposable.dispose()
        timeSubject.onNext(0L)
    }

    fun clear() {
        disposable.dispose()
    }
}
