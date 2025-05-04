package arm.android.rxjavaoperatorstimer


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import arm.android.rxjavaoperatorstimer.databinding.ActivityMainBinding
import arm.android.rxjavaoperatorstimer.timer.dependens.MyApp
import arm.android.rxjavaoperatorstimer.timer.model.MainViewModel
import arm.android.rxjavaoperatorstimer.timer.view.TimerAdapter
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            (application as MyApp).appComponent.inject(this)

            mainViewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

            setupRecyclerView()
            setupObservers()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setupRecyclerView() {
        try {
            val adapter = TimerAdapter(mainViewModel)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setupObservers() {
        try {
            binding.addButton.setOnClickListener {
                try {
                    mainViewModel.addTimer()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}