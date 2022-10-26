package learning.android.kmm.network

import io.ktor.client.call.*
import io.ktor.client.request.*
import learning.android.kmm.network.model.DogResponse
import learning.android.kmm.network.settings.HttpClientSetup

interface NetworkAction {
    suspend fun getDogImageUrl(): DogResponse
}

object NetworkActionImpl: NetworkAction, NetworkErrorGenerator {

    private val client = HttpClientSetup.client

    override suspend fun getDogImageUrl(): DogResponse {
        val response: DogResponse = client.get{ url(ApiRoutes.RANDOM_IMAGE)}.body()
        client.close()

        if (response.status.isNullOrEmpty()) {
            return generateErrorMessage()
        }

        return response
    }
}