package learning.android.compose.ui.screens.main.weather

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import learning.android.compose.R
import learning.android.compose.data.WeatherInfo
import learning.android.compose.ui.screens.main.states.*

class WeatherItemsViewModel: ViewModel() {

    private val _uiState: MutableState<UiState> = mutableStateOf(InitialState())
    val uiState: State<UiState> = _uiState

    var selectedIndex: MutableState<Int> = mutableStateOf(-1)

    var weatherListInfo: List<WeatherInfo> = emptyList()

    fun fetchData(): List<WeatherInfo> {
        // A big time needed for this network(!!!) call
        viewModelScope.launch {
            delay(2000)
            _uiState.value = LoadingState()
            delay(2000)
            _uiState.value = LoadedState()
        }

        weatherListInfo = listOf(
            WeatherInfo(R.drawable.weather_sunny, "Patras", "Sunny weather"),
            WeatherInfo(R.drawable.weather_cloudy, "Athens", "Mostly Cloudy"),
            WeatherInfo(R.drawable.weather_rainy, "Rio", "Rains all day long"),
            WeatherInfo(R.drawable.weather_snowy, "Chania", "White day"),
            WeatherInfo(R.drawable.weather_sunny, "Aigio", "Sun with teeth"),
            WeatherInfo(R.drawable.weather_cloudy, "Far Rachoula", "Clouds"),
            WeatherInfo(R.drawable.weather_rainy, "Volos", "It is raining, drink a tsipouro inside"),
            WeatherInfo(R.drawable.weather_snowy, "Miami", "Wear a jacket today"))

        return weatherListInfo
    }
}