package arm.android.rxjavaoperatorstimer.timer.model


import android.util.Log

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable

import java.util.concurrent.TimeUnit

class TimerViewModel : ViewModel() {


    private val disposables = CompositeDisposable()
    val timerData = MutableLiveData<TimerData>()
    val formattedTime = MediatorLiveData<String>()
    val isRunning: Boolean
        get() = timerData.value?.isRunning ?: false
    init {
        formattedTime.addSource(timerData) { updateFormattedTime(it) }
        timerData.value = TimerData(id = -1)
    }

    private fun updateFormattedTime(data: TimerData) {
        try{
            val minutes = data.elapsedSeconds / 60
            val seconds = data.elapsedSeconds % 60
            formattedTime.value = if (data.showSeconds) {
                String.format("%02d:%02d", minutes, seconds)
            } else {
                String.format("%02d", minutes)
            }
        }catch (e:Exception){

                Log.d("error1", e.toString())

        }
    }

    fun toggleTimer() {
        try{
            timerData.value?.let {
                it.isRunning = !it.isRunning
                if (it.isRunning) startTimer() else stopTimer()
                timerData.postValue(it)
            }
        }catch (e:Exception){

            Log.d("error1", e.toString())

        }
    }

    private fun startTimer() {
        try{
            disposables.add(
                Observable.interval(1, TimeUnit.SECONDS)
                    .subscribe {
                        timerData.value?.elapsedSeconds =
                            timerData.value?.elapsedSeconds?.plus(1) ?: 0
                        timerData.postValue(timerData.value)
                    }
            )
        }catch (e:Exception){
            Log.d("error1", e.toString())
        }
    }

    private fun stopTimer() {
        try{ disposables.clear() }catch (e:Exception){
            Log.d("error1", e.toString())
        }
    }

    override fun onCleared() {
        try{
            disposables.dispose()
            super.onCleared()
        }catch (e:Exception){
            Log.d("error1", e.toString())
}
}

}