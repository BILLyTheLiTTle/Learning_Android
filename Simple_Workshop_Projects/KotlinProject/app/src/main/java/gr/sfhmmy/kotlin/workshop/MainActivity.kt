package gr.sfhmmy.kotlin.workshop

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import gr.sfhmmy.kotlin.workshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var layoutAdapter: ActivityMainBinding? = null
    var viewModel: MainActivityViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, NewInstanceFactory())
                .get(MainActivityViewModel::class.java)
        layoutAdapter = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    fun showGreeting(view: View?) {
        val age = layoutAdapter?.ageEditText?.text.toString().toInt()
        layoutAdapter?.greetingOutputTextView?.text = viewModel?.calculatingGreeting(age, layoutAdapter?.nameEditText?.text.toString())
    }
}