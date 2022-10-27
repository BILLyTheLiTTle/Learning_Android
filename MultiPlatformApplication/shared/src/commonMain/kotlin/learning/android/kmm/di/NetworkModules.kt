package learning.android.kmm.di

import learning.android.kmm.network.NetworkAction
import learning.android.kmm.network.NetworkActionImpl
import learning.android.kmm.network.settings.HttpClientSetup
import learning.android.kmm.network.settings.JsonSerializerSetup
import org.koin.dsl.module

object NetworkModules {
    val networkModule = module {
        single { JsonSerializerSetup() }
        single { HttpClientSetup(get()) }
        single <NetworkAction> { NetworkActionImpl(get()) }
    }
}