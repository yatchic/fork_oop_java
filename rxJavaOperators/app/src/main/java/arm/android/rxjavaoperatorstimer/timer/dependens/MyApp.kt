package arm.android.rxjavaoperatorstimer.timer.dependens

import android.app.Application
import android.util.Log
import arm.android.rxjavaoperatorstimer.timer.model.ErrorDialog

class MyApp : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {

            super.onCreate()
            appComponent = DaggerAppComponent.create()

    }
}