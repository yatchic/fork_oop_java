package arm.android.rxjava

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import arm.android.rxjava.databinding.ActivityMainBinding

import arm.android.rxjava.weathe.WeatherAdapter
import arm.android.rxjava.weathe.WeatherViewModel


import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModel: WeatherViewModel
    private lateinit var binding: ActivityMainBinding
    private val adapter = WeatherAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {

            (applicationContext as MyApp).appComponent.inject(this)
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            binding.recyclerView.adapter = adapter
            setupObservers()

    }

    private fun setupObservers() {
        viewModel.weatherData.observe(this) { data ->
            adapter.submitList(listOf(data))
        }

        viewModel.error.observe(this) { errorMsg ->
            Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
        }
    }


    fun onSearchClicked(view: View) {
        viewModel.fetchWeather(binding.editTextCity.text.toString())
    }
}