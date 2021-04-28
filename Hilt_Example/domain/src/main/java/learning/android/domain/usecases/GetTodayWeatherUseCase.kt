package learning.android.domain.usecases

import learning.android.domain.models.TodayWeatherModel
import learning.android.domain.repositories.RemoteRepo
import javax.inject.Inject

class GetTodayWeatherUseCase @Inject constructor(
    private val city: String,
    private val apiRepo: RemoteRepo
    ): SingleUseCase<TodayWeatherModel> {
    override fun execute() = apiRepo.getTodayWeather(city)
}