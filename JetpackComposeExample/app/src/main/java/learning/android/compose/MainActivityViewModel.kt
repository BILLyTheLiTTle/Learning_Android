package learning.android.compose

import androidx.lifecycle.ViewModel
import learning.android.compose.data.WeatherInfo

class MainActivityViewModel: ViewModel() {

    fun fetchData(): List<WeatherInfo> {
        // These data comes after a network call!
        return listOf(
            WeatherInfo(R.drawable.weather_sunny, "Patras", "Sunny weather"),
            WeatherInfo(R.drawable.weather_cloudy, "Athens", "Mostly Cloudy"),
            WeatherInfo(R.drawable.weather_rainy, "Rio", "Rains all day long"),
            WeatherInfo(R.drawable.weather_snowy, "Chania", "White day"),
            WeatherInfo(R.drawable.weather_sunny, "Aigio", "Sun with teeth"),
            WeatherInfo(R.drawable.weather_cloudy, "Far Rachoula", "Clouds"),
            WeatherInfo(R.drawable.weather_rainy, "Volos", "It is raining, drink a tsipouro inside"),
            WeatherInfo(R.drawable.weather_snowy, "Miami", "Wear a jacket today"))
    }
}