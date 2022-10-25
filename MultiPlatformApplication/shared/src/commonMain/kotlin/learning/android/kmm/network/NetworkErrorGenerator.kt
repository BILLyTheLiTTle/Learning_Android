package learning.android.kmm.network

import learning.android.kmm.network.model.DogResponse

expect interface NetworkErrorGenerator {
    open fun generateErrorMessage(): DogResponse
}