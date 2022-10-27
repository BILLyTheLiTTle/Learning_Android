package learning.android.kmm.android

import android.app.Application
import learning.android.kmm.di.AndroidOtherModules
import learning.android.kmm.di.OtherModules
import org.koin.core.context.startKoin

class KmmApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(AndroidOtherModules.platformModule, OtherModules.greetingModule)
        }
    }
}