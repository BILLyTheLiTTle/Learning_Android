package learning.android.data.apiservice

import io.reactivex.rxjava3.core.Single
import learning.android.data.models.WeatherModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/weather/{city}")
    fun getWeather(@Path("city") city: String): Single<WeatherModel>
}