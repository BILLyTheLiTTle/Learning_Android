package learning.android.hilt.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import learning.android.domain.models.request.City
import learning.android.hilt.R
import learning.android.hilt.databinding.ActivityMainBinding
import learning.android.hilt.viewmodel.MainActivityViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    @Inject
    lateinit var city: City

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout: ActivityMainBinding  = DataBindingUtil.setContentView(this, R.layout.activity_main)

        city.name = "Patras"

        viewModel.getTodayWeatherLiveData().observe(this) {
            layout.cityTextview.text = city.name
            layout.temperatureValueTextview.text = it.temperature
            layout.windValueTextview.text = it.wind
            layout.descriptionValueTextview.text = it.description
        }

        viewModel.getTodayWeather(city)
    }
}