package arm.android.fotoloader.foto.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import arm.android.fotoloader.databinding.ActivityMainBinding
import arm.android.fotoloader.foto.model.PhotoAdapter
import arm.android.fotoloader.foto.view_model.PhotoViewModel
import arm.android.fotoloader.foto.dependencies.MyApp
import javax.inject.Inject

class MainActivity : AppCompatActivity() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: PhotoViewModel
    private lateinit var binding: ActivityMainBinding
    private val adapter = PhotoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {

        (application as MyApp).appComponent.inject(this)


        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel = ViewModelProvider(this, viewModelFactory)[PhotoViewModel::class.java]


        setupRecyclerView()
        observeViewModel()


        viewModel.fetchPhotos()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.photos.observe(this) { photos ->
            adapter.setPhotos(photos)
        }
        viewModel.error.observe(this) { errorMessage ->
            showError(errorMessage)
        }
    }

    private fun showError(message: String?) {
        Toast.makeText(this, message ?: "Unknown error", Toast.LENGTH_SHORT).show()
    }
}
