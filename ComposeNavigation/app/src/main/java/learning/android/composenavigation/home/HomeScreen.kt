package learning.android.composenavigation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import learning.android.composenavigation.navigation.*
import learning.android.feature_one.FeatureOneHome
import learning.android.feature_three.FeatureThreeHome
import learning.android.feature_two.FeatureTwoHome
import learning.android.navigation.destinations.Destination
import learning.android.navigation.navigateToNewScreen

@Composable
fun NavHomeScreen() {
    val navController = rememberNavController()
    val viewModel = hiltViewModel<HomeViewModel>()
    val destinationState by viewModel.navigator.destination.collectAsState()
    LaunchedEffect(destinationState) {
        if (destinationState != Destination.Nowhere()) {
            navController.navigate(destinationState.route)
        }
    }
    NavHost(navController = navController, startDestination = HomeScreen.route) {
        addHomeScreenGraph(viewModel)
        addFeatureOneScreenGraph(navController)
        addFeatureTwoScreenGraph(navController)
        addFeatureThreeScreenGraph(navController)
    }
}

@Composable
fun MainHome(
    name: String,
    viewModel: HomeViewModel
) {
    Column() {
        Text(text = name)

        Button(onClick = { viewModel.navigate(FeatureOneScreen) }) {
            Text(text = "Feature 1")
        }
        Button(onClick = { viewModel.navigate(FeatureTwoScreen) }) {
            Text(text = "Feature 2")
        }
        Button(onClick = { viewModel.navigate(FeatureThreeScreen) }) {
            Text(text = "Feature 3")
        }
    }
}

private fun NavGraphBuilder.addHomeScreenGraph(
    homeViewModel: HomeViewModel
) {
    composable(route = HomeScreen.route) {
        MainHome(
            name = "HOME",
            viewModel = homeViewModel
        )
    }
}

private fun NavGraphBuilder.addFeatureOneScreenGraph(navController: NavController) {
    composable(route = FeatureOneScreen.route) {
        FeatureOneHome(name = "ONE")
    }
}

private fun NavGraphBuilder.addFeatureTwoScreenGraph(navController: NavController) {
    composable(route = FeatureTwoScreen.route) {
        FeatureTwoHome(name = "TWO")
    }
}

private fun NavGraphBuilder.addFeatureThreeScreenGraph(navController: NavController) {
    composable(route = FeatureThreeScreen.route) {
        FeatureThreeHome(name = "THREE")
    }
}