package arm.android.rxjavaoperatorstimer.timer.dependens

import android.app.Application


class MyApp : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {

            super.onCreate()
            appComponent = DaggerAppComponent.create()

    }
}