package arm.android.calendar.calendar.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import arm.android.calendar.calendar.data.ApiFactory.omdbApi
import arm.android.calendar.calendar.data.MovieRepositoryImpl
import arm.android.calendar.calendar.domain.GetMoviesUseCase
import arm.android.calendar.calendar.domain.Movie
import arm.android.calendar.calendar.view_model.DataState
import arm.android.calendar.calendar.view_model.ListViewModel
import arm.android.calendar.calendar.view_model.ListViewModelFactory
import arm.android.calendar.calendar.view_model.MovieAdapter
import arm.android.calendar.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MovieAdapter
    private val disposables = CompositeDisposable()

    private val movieRepository by lazy { MovieRepositoryImpl() }
    private val getMoviesUseCase by lazy { GetMoviesUseCase(movieRepository) }
    private val viewModel: ListViewModel by viewModels {
        ListViewModelFactory(application, getMoviesUseCase)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupSearch()
        observeViewModel()
        viewModel.loadInitialData()

        binding.searchButton.setOnClickListener(){

            omdbApi.searchMovies("${ binding.searchEditText.text}", "4c6f4409")//по-обычному тоже не находит фильмы
        }



    }

    private fun setupRecyclerView() {
        adapter = MovieAdapter { movie: Movie -> }
        binding.moviesRecyclerView.apply {
            adapter = this@MainActivity.adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun setupSearch() {
        binding.searchEditText.doAfterTextChanged { editable ->
            editable?.toString()?.trim()?.let { query ->
                if (query.length >= 3) {
                    viewModel.load(query)
                }
            }
        }
    }

    private fun observeViewModel() {
        viewModel.state.subscribe { state ->
            when (state) {
                is DataState.Loading -> showLoading()
                is DataState.Success -> {
                    hideLoading()
                    adapter.submitList(state.movies)
                }
                is DataState.Error -> {
                    hideLoading()
                    showError(state.message)
                }
            }
        }.let { disposables.add(it) }
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }



    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }


    private fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG)
            .setAction("повторить") { viewModel.load(viewModel.currentQuery) }
            .show()
    }
}