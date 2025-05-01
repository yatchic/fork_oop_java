package arm.android.rxjava.weathe

import retrofit2.http.GET
import retrofit2.http.Query
import io.reactivex.rxjava3.core.Single

interface ApiService {
    @GET("weather")
    fun getWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String
    ): Single<WeatherResponse>
}

data class WeatherResponse(
    val name: String,
    val main: MainData
)

data class MainData(
    val temp: Double,
    val humidity: Int
)