package learning.android.kmm.di

import learning.android.kmm.db.DatabaseDriverFactory
import org.koin.dsl.module

object AndroidDatabaseModules {
    val databaseModule = module {
        single { DatabaseDriverFactory(get()) }
    }
}
