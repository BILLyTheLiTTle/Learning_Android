package learning.android.composenavigation.navigation

sealed class Destination(val route: String) {
    object HomeScreen: Destination("Main Home")
    object FeatureOneScreen: Destination("Feature One")
    object FeatureTwoScreen: Destination("Feature Two")
    object FeatureThreeScreen: Destination("Feature Three")
}