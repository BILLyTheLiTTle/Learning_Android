package learning.android.kmm.android

import android.app.Application
import learning.android.kmm.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KmmApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@KmmApplication)
            modules(
                AndroidOtherModules.platformModule,
                OtherModules.greetingModule,
                NetworkModules.networkModule,
                AndroidDatabaseModules.databaseModule,
                DatabaseModules.databaseModule)
        }
    }
}