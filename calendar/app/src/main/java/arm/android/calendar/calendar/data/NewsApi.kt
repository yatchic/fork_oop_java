package arm.android.calendar.calendar.data



import arm.android.calendar.calendar.domain.News
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("/v2/everything")
    fun getNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String
    ): Single<NewsResponse>
}

data class NewsResponse(val articles: List<News>)