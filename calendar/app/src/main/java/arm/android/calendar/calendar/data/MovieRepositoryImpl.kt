package arm.android.calendar.calendar.data

import arm.android.calendar.calendar.domain.Movie
import arm.android.calendar.calendar.domain.MovieRepository
import arm.android.calendar.calendar.domain.News
import io.reactivex.rxjava3.core.Single
import java.io.IOException

class MovieRepositoryImpl(
    private val omdbApi: OmdbApi = ApiFactory.omdbApi,
    private val newsApi: NewsApi = ApiFactory.newsApi
) : MovieRepository {

    override fun searchMovies(query: String): Single<List<Movie>> {
        return omdbApi.searchMovies(query, "4c6f4409")
            .map { response ->
                if (response.Search.isNullOrEmpty()) {
                    throw EmptyResultsException("фильмы не найдены")
                } else {
                    response.Search
                }
            }
    }

    override fun getNews(movieTitle: String): Single<List<News>> {
        return newsApi.getNews(movieTitle, "c1e1b9a62bfe4317bb75187bdb3eadc8")
            .map { response ->
                if (response.articles.isNullOrEmpty()) {
                    throw EmptyResultsException("новости не найдены")
                } else {
                    response.articles
                }
            }
    }
}


class EmptyResultsException(message: String = "нет результатов по запросу") : IOException(message)