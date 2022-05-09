package learning.android.data.repo

import io.reactivex.rxjava3.core.Single
import learning.android.data.apiservice.ApiService
import learning.android.data.mappers.WeatherMapper
import learning.android.domain.models.request.City
import learning.android.domain.models.response.TodayWeatherModel
import learning.android.domain.repositories.RemoteRepo
import javax.inject.Inject

class RemoteRepoImpl @Inject constructor(
    private val apiService: ApiService,
    private val weatherMapper: dagger.Lazy<WeatherMapper>
    ): RemoteRepo {

    override fun getTodayWeather(city: City): Single<TodayWeatherModel> { // String - In case you want to inject something as String
        return apiService.getWeather(city.name) //city - In case you want to inject something as String
            .map {
                weatherMapper.get().toTodayWeather(it)
            }
    }
}