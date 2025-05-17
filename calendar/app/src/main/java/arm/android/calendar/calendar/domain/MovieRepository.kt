package arm.android.calendar.calendar.domain

import io.reactivex.rxjava3.core.Single

interface MovieRepository {
    fun searchMovies(query: String): Single<List<Movie>>
    fun getNews(movieTitle: String): Single<List<News>>
}