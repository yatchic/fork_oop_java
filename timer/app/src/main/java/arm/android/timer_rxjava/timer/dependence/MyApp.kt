// AppComponent.kt
package arm.android.timer_rxjava.timer.dependence

import android.app.Application

class MyApp : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory()
            .create(this)
    }
}
