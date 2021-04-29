package learning.android.domain.models.response

data class TodayWeatherModel(
    var temperature: String?,
    var wind: String?,
    var description: String?
) {
}