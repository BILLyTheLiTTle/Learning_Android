package learning.android.compose.ui.screens.main.weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import learning.android.compose.R
import learning.android.compose.data.WeatherInfo
import learning.android.compose.ui.list.WeatherItem
import learning.android.compose.ui.theme.JetpackComposeExampleTheme

@Composable
fun WeatherItemsScreen(weatherInfo: List<WeatherInfo>) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier
            .fillMaxHeight(1f)
            .padding(5.dp)) {
        items(weatherInfo) {info -> WeatherItem(weatherInfo = info) }
    }
}

@Preview(showBackground = true)
@Composable
fun DummyWeatherItems() {
    JetpackComposeExampleTheme {
        WeatherItemsScreen(listOf(
            WeatherInfo(R.drawable.weather_sunny, "Patras", "Sunny weather"),
            WeatherInfo(R.drawable.weather_cloudy, "Athens", "Mostly Cloudy"),
            WeatherInfo(R.drawable.weather_rainy, "Rio", "Rains all day long")))
    }
}