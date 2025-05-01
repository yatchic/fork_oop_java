package arm.android.rxjava.weathe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val weatherData = MutableLiveData<WeatherResponse>()
    val error = MutableLiveData<String>()

    fun fetchWeather(city: String) {
        apiService.getWeather(city, "AIzaSyBZ9kyJFxLxzyK6K3lBTu1BisKhXRfiFzA")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response -> weatherData.value = response },
                { error.value = it.message }
            )
            .let { compositeDisposable.add(it) }
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}