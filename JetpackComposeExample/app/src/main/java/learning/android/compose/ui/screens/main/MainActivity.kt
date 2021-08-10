package learning.android.compose.ui.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.rememberCoroutineScope
import com.google.accompanist.pager.*
import learning.android.compose.ui.screens.main.greetings.GreetingsScreen
import learning.android.compose.ui.screens.main.greetings.GreetingsScreenViewModel
import learning.android.compose.ui.screens.main.resume.ResumeScreen
import learning.android.compose.ui.screens.main.weather.WeatherItemsScreen
import learning.android.compose.ui.screens.main.weather.WeatherItemsViewModel
import learning.android.compose.ui.screens.tabbing.Tabs
import learning.android.compose.ui.screens.tabbing.TabsContent
import learning.android.compose.ui.theme.JetpackComposeExampleTheme

class MainActivity : ComponentActivity() {
    private val weatherItemsVM by viewModels<WeatherItemsViewModel>()
    private val greetingsVM by viewModels<GreetingsScreenViewModel>()

    private val tabsItems = arrayOf("Weather", "Greetings", "Resume")

    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val scope = rememberCoroutineScope()
            val pagerState = rememberPagerState(
                pageCount = tabsItems.size
            )
            JetpackComposeExampleTheme {
                Column {
                    TopAppBar(title = {
                        Text(text = "Jetpack Compose")
                    })
                    // A surface container using the 'background' color from the theme
                    Surface(color = MaterialTheme.colors.background) {
                        Column {
                            Tabs(pagerState = pagerState, scope = scope, tabsItems) {
                                pagerState.animateScrollToPage(it)
                            }
                            TabsContent(pagerState = pagerState) {
                                when (it) {
                                    0 -> WeatherItemsScreen(viewModel = weatherItemsVM)
                                    1 -> GreetingsScreen(viewModel = greetingsVM)
                                    else -> ResumeScreen(name = greetingsVM.name.value?:"",
                                        option = if(weatherItemsVM.selectedIndex.value < 0) ""
                                        else weatherItemsVM.weatherListInfo[weatherItemsVM.selectedIndex.value].cityName)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}