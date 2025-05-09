package arm.android.fotoloader.foto.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import arm.android.fotoloader.foto.model.ApiService
import arm.android.fotoloader.foto.model.Photo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo

import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class PhotoViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val photos = MutableLiveData<List<Photo>>()
    val error = MutableLiveData<String>()

    fun fetchPhotos() {
        apiService.getPhotos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ photos.value = it }, { error.value = it.message })
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
