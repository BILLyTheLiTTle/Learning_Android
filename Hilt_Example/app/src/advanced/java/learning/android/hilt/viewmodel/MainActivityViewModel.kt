package learning.android.hilt.viewmodel

import androidx.databinding.Observable
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
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

    @Inject
    lateinit var city: City

    val spinnerCitySelections = ObservableArrayList<String>()
    val selectedCityPosition = ObservableField<Int>()

    private val _todayWeather = MutableLiveData<TodayWeatherModel>()
    fun getTodayWeatherLiveData(): LiveData<TodayWeatherModel> {
        return _todayWeather
    }

    init {
        spinnerCitySelections.addAll(arrayListOf("Patras", "Athens"))
        selectedCityPosition.onPropertyChangedCallback {
            city.name = spinnerCitySelections[it ?: 0]
            getTodayWeather(city)
        }
    }

    private fun <T> ObservableField<T>.onPropertyChangedCallback(
        action: (item: T?) -> Unit
    ) {
        addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(observable: Observable, i: Int) {
                action(get())
            }
        })
    }

    private fun getTodayWeather(city: City) {
        todayWeatherUseCase.city = city
        todayWeatherUseCase.execute()
            .subscribeOn(Schedulers.io())
            .subscribe { value -> _todayWeather.postValue(value) }
    }
}