package learning.android.dogbreeds.ui.screens.breeds.details

sealed class UserActionEvent<out T>

data class NextBreed<out T>(val value: T): UserActionEvent<T>()

data class PreviousBreed<out T>(val value: T): UserActionEvent<T>()