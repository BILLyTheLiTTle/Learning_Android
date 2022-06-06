package learning.android.composenavigation.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import learning.android.navigation.destinations.Destination

object HomeScreen: Destination("Main Home") {
    const val WELCOME_TEXT = "welcome_text"
    const val WELCOME_TEXT_DEFAULT_VALUE = "Hello Home"

    // No need to apply custom arguments but why not have a full example?!
    override val arguments = listOf(
        navArgument(WELCOME_TEXT) {
            defaultValue = WELCOME_TEXT_DEFAULT_VALUE
            type = NavType.StringType
        }
    )

}
object FeatureOneScreen: Destination("Feature One") {
    const val WELCOME_TEXT = "welcome_text"
    const val WELCOME_TEXT_DEFAULT_VALUE = "Hello Feature 1"
    const val ORIGIN_TEXT = "came_from"
    const val ORIGIN_TEXT_DEFAULT_VALUE = "somewhere"
    var originTextValue = ""

    // No need to apply custom arguments but why not have a full example?!
    override val arguments = listOf(
        navArgument(WELCOME_TEXT) {
            defaultValue = WELCOME_TEXT_DEFAULT_VALUE
            type = NavType.StringType
        },
        navArgument(ORIGIN_TEXT) {
            defaultValue= ORIGIN_TEXT_DEFAULT_VALUE
            type = NavType.StringType
        }
    )
}
object FeatureTwoScreen: Destination("Feature Two")
object FeatureThreeScreen: Destination("Feature Three")