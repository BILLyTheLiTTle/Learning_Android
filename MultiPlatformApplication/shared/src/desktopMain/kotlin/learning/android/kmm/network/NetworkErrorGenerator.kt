package learning.android.kmm.network

import learning.android.kmm.network.model.DogResponse

actual interface NetworkErrorGenerator {


    actual fun generateErrorMessage(): DogResponse {
        TODO("Not yet implemented")
    }
}