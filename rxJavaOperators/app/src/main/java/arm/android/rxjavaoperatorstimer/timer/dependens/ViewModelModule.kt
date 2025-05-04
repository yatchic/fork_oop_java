package arm.android.rxjavaoperatorstimer.timer.dependens

import androidx.lifecycle.ViewModel
import arm.android.rxjavaoperatorstimer.timer.model.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}