package learning.android.mvi.states

sealed interface UiState {
    fun render()
}