package learning.android.navigation.destinations

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavOptions

open class Destination(
    open val route: String = "",
    open val arguments: List<NamedNavArgument> = emptyList(),
    open val options: NavOptions? = null
){

    class Nowhere: Destination()
}