package learning.android.kmm.network

import learning.android.kmm.network.model.Pet

actual interface NetworkErrorGenerator {
    actual fun generateErrorMessage(): NetworkState.Error = NetworkState.Error(
        errorIcon = "https://i.stack.imgur.com/4svyQ.png",
        message = "Error"
    )

}