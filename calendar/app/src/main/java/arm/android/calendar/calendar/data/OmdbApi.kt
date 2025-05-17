package arm.android.calendar.calendar.data



import arm.android.calendar.calendar.domain.Movie
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApi {
    @GET("/")
    fun searchMovies(
        @Query("s") query: String,
        @Query("apikey") apiKey: String
    ): Single<MovieResponse>
}

data class MovieResponse(val Search: List<Movie>)