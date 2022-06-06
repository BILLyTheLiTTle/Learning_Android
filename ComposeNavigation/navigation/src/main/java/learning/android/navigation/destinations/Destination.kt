package learning.android.navigation.destinations

import androidx.navigation.NavOptions

open class Destination(
    open val route: String = "",
    open val options: NavOptions? = null
){

    class Nowhere: Destination()
}