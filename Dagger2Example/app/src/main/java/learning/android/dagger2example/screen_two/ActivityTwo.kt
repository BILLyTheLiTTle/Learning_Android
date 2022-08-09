package learning.android.dagger2example.screen_two

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import learning.android.dagger2example.Dagger2ExampleApplication
import learning.android.dagger2example.databinding.ActivityTwoBinding
import learning.android.dagger2example.di.components.ActivityComponent

class ActivityTwo : AppCompatActivity() {

    private lateinit var binding: ActivityTwoBinding

    lateinit var daggerActivityComponent: ActivityComponent
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        daggerActivityComponent = (application as Dagger2ExampleApplication).appComponent.activityComponent().create(this)
        super.onCreate(savedInstanceState)
        binding = ActivityTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}