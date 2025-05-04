package arm.android.rxjavaoperatorstimer.timer.dependens

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import arm.android.rxjavaoperatorstimer.timer.model.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dagger.multibindings.Multibinds
import javax.inject.Provider
import javax.inject.Singleton

@Module
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun bindContext(application: Application): Context

    companion object {
        @Provides
        @Singleton
        fun provideViewModelFactory(
            viewModels: MutableMap<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
        ): ViewModelProvider.Factory {
            return ViewModelFactory(viewModels)
        }
    }
}

