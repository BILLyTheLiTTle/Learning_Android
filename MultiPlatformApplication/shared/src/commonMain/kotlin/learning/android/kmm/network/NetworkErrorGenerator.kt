package learning.android.kmm.network

import learning.android.kmm.network.model.Pet

expect interface NetworkErrorGenerator {
    open fun generateErrorMessage(): NetworkState.Error
}