package learning.android.kmm.di

import learning.android.kmm.AndroidPlatform
import learning.android.kmm.Platform
import org.koin.dsl.module

object AndroidOtherModules {
    val platformModule = module {
        single<Platform> { AndroidPlatform() }
    }
}