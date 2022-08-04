package learning.android.dagger2example.screen_one

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import learning.android.dagger2example.R
import learning.android.dagger2example.databinding.ActivityOneBinding
import learning.android.dagger2example.screen_two.ActivityTwo

class ActivityOne : AppCompatActivity() {

    private lateinit var binding: ActivityOneBinding

    val stringValue = "activity"
    val intValue = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOneBinding.inflate(layoutInflater)

        binding.goToActivityTwoButton.setOnClickListener {
            startActivity(Intent(this@ActivityOne, ActivityTwo::class.java))
        }

        binding.goToFragmentOneButton.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, FragmentOne.newInstance())
                .addToBackStack("fragment_one")
                .commit()
        }

        binding.goToFragmentTwoButton.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, FragmentTwo.newInstance())
                .addToBackStack("fragment_two")
                .commit()
        }

        setContentView(binding.root)
    }
}