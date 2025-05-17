package arm.android.calendar.calendar.view_model

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.ViewModel
import arm.android.calendar.calendar.data.EmptyResultsException
import arm.android.calendar.calendar.domain.GetMoviesUseCase
import arm.android.calendar.calendar.domain.Movie
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import retrofit2.HttpException
import java.io.IOException

sealed class DataState {
    object Loading : DataState()
    data class Success(val movies: List<Movie>) : DataState()
    data class Error(val message: String) : DataState()
}

class ListViewModel(
    private val application: Application,
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _state = BehaviorSubject.create<DataState>()
    val state = _state.hide()
    private val disposables = CompositeDisposable()
    internal var currentQuery = ""

    fun loadInitialData() {
        load("marvel")
    }

    fun load(query: String) {
        currentQuery = query
        if (!hasInternetConnection()) {
            _state.onNext(DataState.Error("нет подключения к интернету"))
            return
        }

        _state.onNext(DataState.Loading)

        getMoviesUseCase(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            // ListViewModel.kt
            .subscribe(
                { movies ->
                    if (movies.isEmpty()) {
                        _state.onNext(DataState.Error("ничего не найдено"))
                    } else {
                        _state.onNext(DataState.Success(movies))
                    }
                },
                { error ->
                    val errorMsg = when (error) {
                        is EmptyResultsException -> "нет результатов по запросу"
                        is IOException -> "ошибка сети: ${error.message}"
                        is HttpException -> "ошибка сервера (${error.code()})"
                        else -> "неизвестная ошибка"
                    }
                    _state.onNext(DataState.Error(errorMsg))
                }
            )
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}