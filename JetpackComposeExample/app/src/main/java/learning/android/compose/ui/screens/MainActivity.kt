package learning.android.compose.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import learning.android.compose.ui.theme.JetpackComposeExampleTheme

class MainActivity : ComponentActivity() {
    val weatherItemsVM by viewModels<WeatherItemsViewModel>()
    val greetingsVM by viewModels<GreetingsScreenViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeExampleTheme {
                Column {
                    TopAppBar(title = {
                        Text(text = "Jetpack Compose")
                    })
                    // A surface container using the 'background' color from the theme
                    Surface(color = MaterialTheme.colors.background) {
                        //WeatherItemsScreen(weatherItemsVM.fetchData())
                        GreetingsScreen(viewModel = greetingsVM)
                    }
                }

            }
        }
    }
}