package learning.android.navigation

import kotlinx.coroutines.flow.StateFlow
import learning.android.navigation.destinations.Destination

interface Navigator {
    val destination: StateFlow<Destination>
    fun navigate(destination: Destination)
}