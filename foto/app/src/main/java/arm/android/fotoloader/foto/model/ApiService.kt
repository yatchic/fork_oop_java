package arm.android.fotoloader.foto.model

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ApiService {
    @GET("photos")
    fun getPhotos(): Single<List<Photo>>
}
