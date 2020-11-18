package learning.android.bottomnav_mvi_navgraph.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import learning.android.bottomnav_mvi_navgraph.R
import learning.android.bottomnav_mvi_navgraph.ui.MyViewModel


/**
 * A simple [Fragment] subclass.
 */
class FirstFragment : Fragment() {

    private lateinit var model: MyViewModel
    private lateinit var textView: TextView
    private lateinit var button: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        textView = view.findViewById(R.id.original_number_textView)
        button = view.findViewById(R.id.plus_button)
        button.setOnClickListener { stepIncrease() }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Get the same ViewModel instance across Fragments
        model = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
        model.originalNumber.observe(viewLifecycleOwner) {
            textView.text = it.toString()
        }
    }

    fun stepIncrease() {
        model.stepIncrease()
    }
}