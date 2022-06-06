package learning.android.composenavigation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import learning.android.composenavigation.navigation.Destination
import learning.android.feature_one.FeatureOneHome
import learning.android.feature_three.FeatureThreeHome
import learning.android.feature_two.FeatureTwoHome
import learning.android.navigation.navigateToNewScreen

@Composable
fun NavHomeScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destination.HomeScreen.route) {
        addHomeScreenGraph(navController)
        addFeatureOneScreenGraph(navController)
        addFeatureTwoScreenGraph(navController)
        addFeatureThreeScreenGraph(navController)
    }
}

@Composable
fun MainHome(
    name: String,
    navigateFeatureOne: (String) -> Unit,
    navigateFeatureTwo: (String) -> Unit,
    navigateFeatureThree: (String) -> Unit,
) {
    Column() {
        Button(onClick = { navigateFeatureOne("Feature One") }) {
            Text(text = "Feature 1")
        }
        Button(onClick = { navigateFeatureTwo("Feature Two") }) {
            Text(text = "Feature 2")
        }
        Button(onClick = { navigateFeatureThree("Feature Three") }) {
            Text(text = "Feature 3")
        }
    }
}

private fun NavGraphBuilder.addHomeScreenGraph(navController: NavController) {
    composable(route = Destination.HomeScreen.route) {
        MainHome(
            name = "HOME",
            navigateFeatureOne = {
                navController.navigateToNewScreen(Destination.FeatureOneScreen.route)
            },
            navigateFeatureTwo = {
                navController.navigateToNewScreen(Destination.FeatureTwoScreen.route)
            },
            navigateFeatureThree = {
                navController.navigateToNewScreen(Destination.FeatureThreeScreen.route)
            },
        )
    }
}

private fun NavGraphBuilder.addFeatureOneScreenGraph(navController: NavController) {
    composable(route = Destination.FeatureOneScreen.route) {
        FeatureOneHome(name = "ONE")
    }
}

private fun NavGraphBuilder.addFeatureTwoScreenGraph(navController: NavController) {
    composable(route = Destination.FeatureTwoScreen.route) {
        FeatureTwoHome(name = "TWO")
    }
}

private fun NavGraphBuilder.addFeatureThreeScreenGraph(navController: NavController) {
    composable(route = Destination.FeatureThreeScreen.route) {
        FeatureThreeHome(name = "THREE")
    }
}