package learning.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

fun NavController.navigateToNewScreen(route: String) {
    navigate(route)
}

fun navigateToOldScreen() {

}

fun navigateBack() {

}