package arm.android.rxjavaoperatorstimer.timer.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

// MainViewModel.kt
class MainViewModel @Inject constructor() : ViewModel() {
    private val _timers = MutableLiveData<MutableList<TimerViewModel>>(mutableListOf())
    val timers: LiveData<MutableList<TimerViewModel>> get() = _timers

    private var nextId = 0

    fun addTimer() {
        val timerVM = TimerViewModel()
        timerVM.timerData.value = TimerData(id = nextId++)
        _timers.value?.add(timerVM)
        _timers.postValue(_timers.value) // Уведомить об изменении
    }
}