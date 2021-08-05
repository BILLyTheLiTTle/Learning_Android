package learning.android.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import learning.android.compose.data.WeatherInfo
import learning.android.compose.ui.list.WeatherItem
import learning.android.compose.ui.theme.JetpackComposeExampleTheme

class MainActivity : ComponentActivity() {
    val viewModel by viewModels<MainActivityViewModel>()

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
                        WeatherItems(viewModel.fetchData())
                    }
                }

            }
        }
    }
}

@Composable
fun WeatherItems(weatherInfo: List<WeatherInfo>) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier.fillMaxHeight(1f).padding(5.dp)) {
        items(weatherInfo) {info -> WeatherItem(weatherInfo = info)}
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeExampleTheme {
        WeatherItems(listOf(
            WeatherInfo(R.drawable.weather_sunny, "Patras", "Sunny weather"),
            WeatherInfo(R.drawable.weather_cloudy, "Athens", "Mostly Cloudy"),
            WeatherInfo(R.drawable.weather_rainy, "Rio", "Rains all day long")))
    }
}