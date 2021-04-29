package learning.android.domain.repositories

import io.reactivex.rxjava3.core.Single
import learning.android.domain.models.request.City
import learning.android.domain.models.response.TodayWeatherModel

interface RemoteRepo {
    fun getTodayWeather(city: City): Single<TodayWeatherModel> //String - In case you want to inject something as String
}