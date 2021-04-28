package learning.android.data.repo

import io.reactivex.rxjava3.core.Single
import learning.android.data.apiservice.ApiService
import learning.android.data.mappers.WeatherMapper
import learning.android.domain.models.TodayWeatherModel
import learning.android.domain.repositories.RemoteRepo
import javax.inject.Inject

class RemoteRepoImpl @Inject constructor(
    private val apiService: ApiService,
    private val weatherMapper: Lazy<WeatherMapper>
    ): RemoteRepo {

    override fun getTodayWeather(city: String): Single<TodayWeatherModel> {
        return apiService.getWeather(city)
            .map {
                weatherMapper.value.toTodayWeather(it)
            }
    }
}