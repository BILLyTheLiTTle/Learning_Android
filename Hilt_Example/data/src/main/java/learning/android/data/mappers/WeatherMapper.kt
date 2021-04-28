package learning.android.data.mappers

import learning.android.data.models.WeatherModel
import learning.android.domain.models.TodayWeatherModel
import javax.inject.Inject

class WeatherMapper @Inject constructor() {

    fun toTodayWeather(weatherServerModel: WeatherModel): TodayWeatherModel {
        return TodayWeatherModel(
            weatherServerModel.temperature ?: "",
            weatherServerModel.wind ?: "",
            weatherServerModel.description ?: "")
    }
}