package learning.android.compose.ui.screens.main.states

sealed interface UiState {
    val id: String
    val loading: Boolean
}

data class InitialState(
    override val id: String = "loading_state",
    override val loading: Boolean = true
) : UiState

data class LoadingState(
    override val id: String = "loading_state",
    override val loading: Boolean = true
) : UiState

data class LoadedState(
    override val id: String = "loaded_state",
    override val loading: Boolean = false
) : UiState