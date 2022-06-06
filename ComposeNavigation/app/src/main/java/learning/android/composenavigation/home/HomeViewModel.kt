package learning.android.composenavigation.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import learning.android.composenavigation.navigation.HomeScreen
import learning.android.navigation.Navigator
import learning.android.navigation.destinations.Destination
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val navigator: Navigator
): ViewModel() {

    init {
        navigator.navigate(HomeScreen)
    }

    /*
    There is no real meaning behind using this flag.
    Read the description in the function below.
     */
    var dummyNavigationFlag: Boolean = false
    fun navigate(destination: Destination) {
        /*
        Sometimes user should not proceed to next screen if he/she not
        fulfills some standards (CAPTCHA result, success/fail for login, etc)
        This flag variable works as a dummy functionality for fail/success.
         */
        dummyNavigationFlag = true
        if (dummyNavigationFlag) {
            navigator.navigate(destination)
        }
    }
}