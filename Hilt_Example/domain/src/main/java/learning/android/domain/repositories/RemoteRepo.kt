package learning.android.domain.repositories

import io.reactivex.rxjava3.core.Single
import learning.android.domain.models.TodayWeatherModel

interface RemoteRepo {
    fun getTodayWeather(city: String): Single<TodayWeatherModel>
}