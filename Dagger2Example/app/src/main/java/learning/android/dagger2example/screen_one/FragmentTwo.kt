package learning.android.dagger2example.screen_one

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import learning.android.dagger2example.Dagger2ExampleApplication
import learning.android.dagger2example.databinding.*

class FragmentTwo : Fragment() {

    private lateinit var binding: FragmentTwoBinding

    val stringValue = "fragment_two"
    val intValue = 200

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTwoBinding.inflate(inflater, container, false)

        // populate application data
        val application = (activity?.application as Dagger2ExampleApplication)
        val appDataBinding = DataApplicationBinding.bind(binding.root)
        appDataBinding.applicationNameValueTextView.text = application.toString()
        appDataBinding.applicationStringValueTextView.text = "${application.stringValue} @ ${Integer.toHexString(application.stringValue.hashCode())}"
        appDataBinding.applicationIntValueTextView.text = "${application.intValue} @ ${Integer.toHexString(application.intValue.hashCode())}"

        // populate activity data
        val activityOne = (activity as ActivityOne)
        val activityDataBinding = DataActivityBinding.bind(binding.root)
        activityDataBinding.activityNameValueTextView.text = activityOne.toString()
        activityDataBinding.activityStringValueTextView.text = "${activityOne.stringValue} @ ${Integer.toHexString(activityOne.stringValue.hashCode())}"
        activityDataBinding.activityIntValueTextView.text = "${activityOne.intValue} @ ${Integer.toHexString(activityOne.intValue.hashCode())}"

        // populate fragment data
        val fragmentDataBinding = DataFragmentBinding.bind(binding.root)
        fragmentDataBinding.fragmentNameValueTextView.text = this.toString()
        fragmentDataBinding.fragmentStringValueTextView.text = "$stringValue @ ${Integer.toHexString(stringValue.hashCode())}"
        fragmentDataBinding.fragmentIntValueTextView.text = "$intValue @ ${Integer.toHexString(intValue.hashCode())}"

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentTwo()
    }
}