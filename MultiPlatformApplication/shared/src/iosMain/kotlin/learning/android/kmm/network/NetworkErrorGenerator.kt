package learning.android.kmm.network

actual interface NetworkErrorGenerator {


    actual fun generateErrorMessage() = NetworkState.Error(
        errorIcon = "https://www.macworld.com/wp-content/uploads/2022/04/iPhone-Warning-800.jpg",
        message = "Error"
    )
}