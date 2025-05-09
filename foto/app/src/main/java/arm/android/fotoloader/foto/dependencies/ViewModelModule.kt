package arm.android.fotoloader.foto.dependencies



import androidx.lifecycle.ViewModelProvider
import arm.android.fotoloader.foto.view_model.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory
}

