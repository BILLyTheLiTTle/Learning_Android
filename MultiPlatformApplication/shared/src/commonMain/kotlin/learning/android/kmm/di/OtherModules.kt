package learning.android.kmm.di

import learning.android.kmm.Greeting
import org.koin.dsl.module

object OtherModules {
    val greetingModule = module {
        single { Greeting(get()) }
    }
}