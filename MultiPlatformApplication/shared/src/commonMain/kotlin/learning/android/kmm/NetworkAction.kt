package learning.android.kmm

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import learning.android.kmm.network.model.ApiRoutes

interface NetworkAction {
    suspend fun getDogImageUrl(): String
}

expect interface NetworkErrorGenerator {
    open fun generateErrorMessage(): String
}

class NetworkActionImpl: NetworkAction, NetworkErrorGenerator {
    private val client = HttpClient()

    override suspend fun getDogImageUrl(): String {
        val result: String

        val response = client.get{ url(ApiRoutes.RANDOM_IMAGE)}
        client.close()

        if (response.status.value != 200) {
            return generateErrorMessage()
        }

        return response.body()
    }
}