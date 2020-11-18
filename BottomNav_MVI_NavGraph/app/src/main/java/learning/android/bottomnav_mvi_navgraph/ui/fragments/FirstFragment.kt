package learning.android.bottomnav_mvi_navgraph.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import learning.android.bottomnav_mvi_navgraph.R
import learning.android.bottomnav_mvi_navgraph.ui.MyViewModel


/**
 * A simple [Fragment] subclass.
 */
class FirstFragment : Fragment() {

    private val model by viewModels<MyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        val textView = view.findViewById<TextView>(R.id.original_number_textView)

        model.originalNumber.observe(viewLifecycleOwner) {
            textView.text = it.toString()
        }
        return view
    }
}