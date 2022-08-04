package learning.android.dagger2example.screen_one

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import learning.android.dagger2example.Dagger2ExampleApplication
import learning.android.dagger2example.databinding.DataActivityBinding
import learning.android.dagger2example.databinding.DataApplicationBinding
import learning.android.dagger2example.databinding.DataFragmentBinding
import learning.android.dagger2example.databinding.FragmentOneBinding

class FragmentOne : Fragment() {

    private lateinit var binding: FragmentOneBinding

    val stringValue = "fragment"
    val intValue = 100

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOneBinding.inflate(inflater, container, false)

        // populate application data
        val application = (activity?.application as Dagger2ExampleApplication)
        val appDataBinding = DataApplicationBinding.bind(binding.root)
        appDataBinding.applicationNameValueTextView.text = application.toString()
        appDataBinding.applicationStringValueTextView.text = application.stringValue
        appDataBinding.applicationIntValueTextView.text = application.intValue.toString()

        // populate activity data
        val activityOne = (activity as ActivityOne)
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
        fun newInstance() = FragmentOne()
    }
}