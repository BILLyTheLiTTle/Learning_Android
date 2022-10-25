package learning.android.kmm.network

import learning.android.kmm.network.model.DogResponse

actual interface NetworkErrorGenerator {


    actual fun generateErrorMessage() = DogResponse(
        message = "https://www.macworld.com/wp-content/uploads/2022/04/iPhone-Warning-800.jpg",
        status = "Error"
    )
}