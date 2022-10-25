package learning.android.kmm.network

import learning.android.kmm.network.model.DogResponse

actual interface NetworkErrorGenerator {
    actual fun generateErrorMessage()= DogResponse(
        message = "https://i.stack.imgur.com/4svyQ.png",
        status = "Error"
    )

}