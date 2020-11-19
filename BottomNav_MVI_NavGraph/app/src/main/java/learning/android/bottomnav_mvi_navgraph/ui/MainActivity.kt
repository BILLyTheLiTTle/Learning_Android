package learning.android.bottomnav_mvi_navgraph.ui


import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import learning.android.bottomnav_mvi_navgraph.R
import learning.android.bottomnav_mvi_navgraph.ui.fragments.FirstFragment
import learning.android.bottomnav_mvi_navgraph.ui.fragments.SecondFragment
import learning.android.bottomnav_mvi_navgraph.ui.fragments.ThirdFragment


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private val model by viewModels<MyViewModel>()
    private lateinit var navigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navigation.setOnNavigationItemSelectedListener(this)

        loadFragment(FirstFragment())
    }

    private fun loadFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        supportFragmentManager.popBackStack()
        transaction.commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragment: Fragment
        when (item.itemId) {
            R.id.first_page_menu_item -> {
                //toolbar.setTitle("Shop")
                fragment = FirstFragment()
            }
            R.id.second_page_menu_item -> {
                //toolbar.setTitle("My Gifts")
                fragment = SecondFragment()
            }
            R.id.third_page_menu_item -> {
                //toolbar.setTitle("Cart")
                fragment = ThirdFragment()
            }
            else -> {
                return false
            }
        }
        loadFragment(fragment)
        return true
    }

    /*
    Solution found here: https://stackoverflow.com/questions/43870485/how-to-handle-bottom-navigation-perfectly-with-back-pressed
    Don't add to backstack
     */
    override fun onBackPressed() {
        val selectedItemId = navigation.selectedItemId
        if (R.id.first_page_menu_item == selectedItemId) {
            super.onBackPressed()
        } else {
            navigation.selectedItemId = R.id.first_page_menu_item
        }
    }
}