package learning.android.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import learning.android.navigation.destinations.Destination

class NavigatorImpl: Navigator {
    private val _destination = MutableStateFlow<Destination>(Destination.Nowhere())
    override val destination = _destination.asStateFlow()

    override fun navigate(destination: Destination) {
        _destination.value = destination
    }
}