package learning.android.dagger2example.screen_two

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import learning.android.dagger2example.Dagger2ExampleApplication
import learning.android.dagger2example.databinding.*

class FragmentThree : Fragment() {
    private lateinit var binding: FragmentThreeBinding

    val stringValue = "fragment_three"
    val intValue = 300

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentThreeBinding.inflate(inflater, container, false)

        // populate application data
        val application = (activity?.application as Dagger2ExampleApplication)
        val appDataBinding = DataApplicationBinding.bind(binding.root)
        appDataBinding.applicationNameValueTextView.text = application.toString()
        appDataBinding.applicationStringValueTextView.text = application.stringValue
        appDataBinding.applicationIntValueTextView.text = application.intValue.toString()

        // populate activity data
        val activityOne = (activity as ActivityTwo)
        val activityDataBinding = DataActivityBinding.bind(binding.root)
        activityDataBinding.activityNameValueTextView.text = activityOne.toString()
        activityDataBinding.activityStringValueTextView.text = activityOne.stringValue
        activityDataBinding.activityIntValueTextView.text = activityOne.intValue.toString()

        // populate fragment data
        val fragmentDataBinding = DataFragmentBinding.bind(binding.root)
        fragmentDataBinding.fragmentNameValueTextView.text = this.toString()
        fragmentDataBinding.fragmentStringValueTextView.text = stringValue
        fragmentDataBinding.fragmentIntValueTextView.text = intValue.toString()

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentThree()
    }
}