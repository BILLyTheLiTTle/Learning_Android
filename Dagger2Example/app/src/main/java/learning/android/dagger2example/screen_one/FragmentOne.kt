package learning.android.dagger2example.screen_one

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import learning.android.dagger2example.Dagger2ExampleApplication
import learning.android.dagger2example.R
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
        binding.applicationNameValueTextView.text = application.toString()
        binding.applicationStringValueTextView.text = application.stringValue
        binding.applicationIntValueTextView.text = application.intValue.toString()

        // populate activity data
        val activityOne = (activity as ActivityOne)
        binding.activityNameValueTextView.text = activityOne.toString()
        binding.activityStringValueTextView.text = activityOne.stringValue
        binding.activityIntValueTextView.text = activityOne.intValue.toString()

        // populate fragment data
        binding.fragmentNameValueTextView.text = this.toString()
        binding.fragmentStringValueTextView.text = stringValue
        binding.fragmentIntValueTextView.text = intValue.toString()

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentOne()
    }
}