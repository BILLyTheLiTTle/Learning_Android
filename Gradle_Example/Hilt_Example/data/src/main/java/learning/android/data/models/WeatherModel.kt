package learning.android.data.models


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherModel(
    @Expose
    @SerializedName("temperature")
    val temperature: String?,

    @Expose
    @SerializedName("wind")
    val wind: String?,

    @Expose
    @SerializedName("description")
    val description: String?,

    @Expose
    @SerializedName("forecast")
    val forecast: List<ForecastModel>?
) {
}