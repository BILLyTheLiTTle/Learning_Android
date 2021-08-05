package learning.android.compose.data

import androidx.annotation.DrawableRes

data class WeatherInfo(
    @DrawableRes val imageResource: Int,
    val cityName: String,
    val description: String
)
