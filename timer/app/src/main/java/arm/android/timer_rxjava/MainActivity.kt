package arm.android.timer_rxjava

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import arm.android.timer_rxjava.databinding.ActivityMainBinding
import arm.android.timer_rxjava.timer.dependence.MyApp
import arm.android.timer_rxjava.timer.model.TimerItemView


import javax.inject.Inject

class MainActivity  @Inject constructor(): AppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (application as MyApp).appComponent.inject(this)
        TimerItemView (this , binding)







    }


}