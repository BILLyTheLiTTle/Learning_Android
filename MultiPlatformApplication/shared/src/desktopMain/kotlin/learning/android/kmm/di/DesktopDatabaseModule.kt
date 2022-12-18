package learning.android.kmm.di

import learning.android.kmm.db.DatabaseDriverFactory
import org.koin.dsl.module

object DesktopDatabaseModule {
    val databaseModule = module {
        single { DatabaseDriverFactory() }
    }
}