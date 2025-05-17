package arm.android.calendar.calendar.domain

import io.reactivex.rxjava3.core.Single

class GetMoviesUseCase(
    private val repository: MovieRepository
) {
    operator fun invoke(query: String): Single<List<Movie>> {
        return repository.searchMovies(query)
    }
}