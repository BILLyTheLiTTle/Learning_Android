package learning.android.hilt.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.skydoves.balloon.*
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
    lateinit var city: City // String - In case you want to inject something as String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout: ActivityMainBinding  = DataBindingUtil.setContentView(this, R.layout.activity_main)

        city.name = "Patras" //city = "Patras" - In case you want to inject something as String

        viewModel.getTodayWeatherLiveData().observe(this) {
            layout.cityTextview.text = city.name // city - In case you want to inject something as String
            layout.temperatureValueTextview.text = it.temperature
            layout.windValueTextview.text = it.wind
            layout.descriptionValueTextview.text = it.description
        }

        viewModel.getTodayWeather(city)

        val balloon = createBalloon(this) {
            setWidthRatio(1.0f)
            setHeight(BalloonSizeSpec.WRAP)
            setText("Edit your profile here!")
            setTextSize(15f)
            setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
            setArrowSize(10)
            setArrowPosition(0.5f)
            setPadding(12)
            setCornerRadius(8f)
            setBalloonAnimation(BalloonAnimation.ELASTIC)
            setLifecycleOwner(lifecycleOwner)
            build()
        }
        layout.descriptionTextview.showAlignTop(balloon, 5, 5)
    }
}