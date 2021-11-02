package learning.android.dogbreeds.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import learning.android.dogbreeds.BuildConfig

@HiltAndroidApp
class DogBreedsApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        System.setProperty("kotlinx.coroutines.debug",
            if(BuildConfig.DEBUG) "on" else "off")
    }
}