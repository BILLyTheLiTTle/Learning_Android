package learning.android.kmm.di

import learning.android.kmm.db.Database
import learning.android.kmm.db.Repository
import org.koin.dsl.module

object DatabaseModules {
    val databaseModule = module {
        single { Database(get()) }
        single { Repository() }
    }
}