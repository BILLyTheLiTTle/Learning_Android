package learning.android.bottomnav_mvi_navgraph.ui


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import learning.android.bottomnav_mvi_navgraph.R
import learning.android.bottomnav_mvi_navgraph.ui.fragments.FirstFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(FirstFragment())
    }

    private fun loadFragment(fragment: Fragment, replace: Boolean = false) {
        val transaction = supportFragmentManager.beginTransaction()
        if(replace)
            transaction.replace(R.id.fragment_container, fragment)
        else
            transaction.add(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}