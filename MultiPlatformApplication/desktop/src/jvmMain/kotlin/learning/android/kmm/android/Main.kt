package learning.android.kmm.android

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import learning.android.kmm.getPlatform

fun main() {
    application {
        val windowState = rememberWindowState()
        Window(
            onCloseRequest = ::exitApplication,
            state = windowState,
            title = "Multiplatform Application"
        ) {
            Surface(modifier = Modifier.fillMaxSize()) {
                Text(getPlatform().name)
            }
        }
    }
}