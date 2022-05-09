package learning.android.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ForecastModel(
    @Expose
    @SerializedName("day")
    val day: String?,

    @Expose
    @SerializedName("temperature")
    val temperature: String?,

    @Expose
    @SerializedName("wind")
    val wind: String?
) {
}