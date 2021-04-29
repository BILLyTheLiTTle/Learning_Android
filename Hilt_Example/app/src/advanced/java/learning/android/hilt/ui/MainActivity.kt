package learning.android.hilt.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import learning.android.hilt.R
import learning.android.hilt.databinding.ActivityMainBinding
import learning.android.hilt.viewmodel.MainActivityViewModel


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout: ActivityMainBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        layout.viewModel = viewModel

        viewModel.getTodayWeatherLiveData().observe(this) {
            layout.temperatureValueTextview.text = it.temperature
            layout.windValueTextview.text = it.wind
            layout.descriptionValueTextview.text = it.description
        }
    }
}