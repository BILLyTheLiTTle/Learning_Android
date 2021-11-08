package learning.android.dogbreeds.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import learning.android.dogbreeds.ui.screens.breeds.details.BreedDetails
import learning.android.dogbreeds.ui.screens.breeds.list.BreedsList

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController,
        startDestination = NavigationRoutes.BREED_LIST_ROUTE) {
        composable(NavigationRoutes.BREED_LIST_ROUTE) { BreedsList(navController) }
        composable(NavigationRoutes.BREED_DETAILS_ROUTE) {
            BreedDetails(it.arguments?.getString(NavigationRoutes.BREED_ID_QUERY, "") ?: "")
        }
    }
}

object NavigationRoutes {
    const val BREED_LIST_ROUTE = "breed_list"

    const val BREED_DETAILS = "breed_details/"
    const val BREED_ID_QUERY = "breed_id"
    const val BREED_DETAILS_ROUTE = "${BREED_DETAILS}{${BREED_ID_QUERY}}"
}