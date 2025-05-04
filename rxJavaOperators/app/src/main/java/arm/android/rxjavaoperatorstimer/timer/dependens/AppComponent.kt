package arm.android.rxjavaoperatorstimer.timer.dependens

import androidx.lifecycle.ViewModelProvider
import arm.android.rxjavaoperatorstimer.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun viewModelFactory(): ViewModelProvider.Factory
}