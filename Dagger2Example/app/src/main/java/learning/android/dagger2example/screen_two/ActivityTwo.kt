package learning.android.dagger2example.screen_two

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import learning.android.dagger2example.databinding.ActivityTwoBinding

class ActivityTwo : AppCompatActivity() {

    private lateinit var binding: ActivityTwoBinding

    val stringValue = "activity_two"
    val intValue = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}