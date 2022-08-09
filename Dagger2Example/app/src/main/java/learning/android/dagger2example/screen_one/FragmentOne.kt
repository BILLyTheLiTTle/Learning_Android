package learning.android.dagger2example.screen_one

import android.content.Context
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
import learning.android.dagger2example.di.components.FragmentComponent
import java.lang.StringBuilder
import javax.inject.Inject
import javax.inject.Named

class FragmentOne : Fragment() {

    private lateinit var binding: FragmentOneBinding

    @Inject
    @Named("application_string")
    lateinit var appString: StringBuilder
    @Inject
    @Named("application_int")
    lateinit var appInt: StringBuilder

    @Inject
    @Named("activity_string")
    lateinit var activityString: StringBuilder
    @Inject
    @Named("activity_int")
    lateinit var activityInt: StringBuilder

    @Inject
    @Named("fragment_string")
    lateinit var fragmentString: StringBuilder
    @Inject
    @Named("fragment_int")
    lateinit var fragmentInt: StringBuilder

    lateinit var daggerFragmentComponent: FragmentComponent
        private set

    override fun onAttach(context: Context) {
        super.onAttach(context)
        daggerFragmentComponent = (activity as ActivityOne).daggerActivityComponent.fragmentComponent().create(this)
        daggerFragmentComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOneBinding.inflate(inflater, container, false)

        // populate application data
        val application = (activity?.application as Dagger2ExampleApplication)
        val appDataBinding = DataApplicationBinding.bind(binding.root)
        appDataBinding.applicationNameValueTextView.text = application.toString()
        appDataBinding.applicationStringValueTextView.text = "${appString} @ ${Integer.toHexString(appString.hashCode())}"
        appDataBinding.applicationIntValueTextView.text = "${appInt} @ ${Integer.toHexString(appInt.hashCode())}"

        // populate activity data
        val activityOne = (activity as ActivityOne)
        val activityDataBinding = DataActivityBinding.bind(binding.root)
        activityDataBinding.activityNameValueTextView.text = activityOne.toString()
        activityDataBinding.activityStringValueTextView.text = "${activityString} @ ${Integer.toHexString(activityString.hashCode())}"
        activityDataBinding.activityIntValueTextView.text = "${activityInt} @ ${Integer.toHexString(activityInt.hashCode())}"

        // populate fragment data
        val fragmentDataBinding = DataFragmentBinding.bind(binding.root)
        fragmentDataBinding.fragmentNameValueTextView.text = this.toString()
        fragmentDataBinding.fragmentStringValueTextView.text = "$fragmentString @ ${Integer.toHexString(fragmentString.hashCode())}"
        fragmentDataBinding.fragmentIntValueTextView.text = "$fragmentInt @ ${Integer.toHexString(fragmentInt.hashCode())}"

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentOne()
    }
}