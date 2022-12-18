package learning.android.kmm.android

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import learning.android.kmm.Greeting
import learning.android.kmm.JvmPlatform
import learning.android.kmm.android.ui.Greeting
import learning.android.kmm.android.ui.SampleGreeting
import learning.android.kmm.db.Repository
import learning.android.kmm.di.DatabaseModules
import learning.android.kmm.di.DesktopDatabaseModule
import learning.android.kmm.di.NetworkModules
import learning.android.kmm.di.OtherModules
import learning.android.kmm.getPlatform
import learning.android.kmm.network.NetworkAction
import learning.android.kmm.network.NetworkActionImpl
import learning.android.kmm.network.settings.HttpClientSetup
import learning.android.kmm.network.settings.JsonSerializerSetup
import org.koin.core.context.startKoin

fun main() {
    startKoin {
        modules(
            OtherModules.greetingModule,
            NetworkModules.networkModule,
            DesktopDatabaseModule.databaseModule,
            DatabaseModules.databaseModule)
    }

    val greeting = Greeting(JvmPlatform())
    val network: NetworkAction = NetworkActionImpl(HttpClientSetup(JsonSerializerSetup()))
    val repository = Repository()

    application {
        val windowState = rememberWindowState()
        Window(
            onCloseRequest = ::exitApplication,
            state = windowState,
            title = "Multiplatform Application"
        ) {
            Surface(modifier = Modifier.fillMaxSize()) {
                repository.resetData()
                repository.addData(0, "")
                Greeting(greeting, network, repository)
//                SampleGreeting(JvmPlatform())
            }
        }
    }
}