package arm.android.calendar.calendar.view_model

import android.app.Application

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import arm.android.calendar.calendar.domain.GetMoviesUseCase

class ListViewModelFactory(
    private val application: Application,
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListViewModel(application, getMoviesUseCase) as T
    }
}