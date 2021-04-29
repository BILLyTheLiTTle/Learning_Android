package learning.android.hilt.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import learning.android.domain.models.request.City
import learning.android.domain.models.response.TodayWeatherModel
import learning.android.domain.usecases.GetTodayWeatherUseCase
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val todayWeatherUseCase: GetTodayWeatherUseCase
) : ViewModel() {

    private val _todayWeather = MutableLiveData<TodayWeatherModel>()
    fun getTodayWeatherLiveData(): LiveData<TodayWeatherModel> {
        return _todayWeather
    }

    fun getTodayWeather(city: City) {
        todayWeatherUseCase.city = city
        todayWeatherUseCase.execute()
            .subscribeOn(Schedulers.io())
            .subscribe { value -> _todayWeather.postValue(value) }
    }
}