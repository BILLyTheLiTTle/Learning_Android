package learning.android.kmm.network

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import learning.android.kmm.network.model.ApiRoutes
import learning.android.kmm.network.model.DogResponse

interface NetworkAction {
    suspend fun getDogImageUrl(): DogResponse
}

class NetworkActionImpl: NetworkAction, NetworkErrorGenerator {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
                encodeDefaults = false
            })
        }
    }

    override suspend fun getDogImageUrl(): DogResponse {
        val response: DogResponse = client.get{ url(ApiRoutes.RANDOM_IMAGE)}.body()
        client.close()

        if (response.status.isNullOrEmpty()) {
            return generateErrorMessage()
        }

        return response
    }
}