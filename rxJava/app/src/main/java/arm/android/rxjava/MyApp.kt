package arm.android.rxjava

import android.app.Application
import arm.android.rxjava.weathe.AppComponent
import arm.android.rxjava.weathe.DaggerAppComponent

class MyApp : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}