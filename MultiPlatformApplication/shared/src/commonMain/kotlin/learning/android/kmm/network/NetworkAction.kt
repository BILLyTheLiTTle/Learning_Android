package learning.android.kmm.network

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import learning.android.kmm.network.model.Pet
import learning.android.kmm.network.settings.HttpClientSetup

interface NetworkAction {
    suspend fun getPetData(): NetworkState<List<Pet>>
}

class NetworkActionImpl(httpClientSetup: HttpClientSetup): NetworkAction, NetworkErrorGenerator {

    private val client = httpClientSetup.client

    override suspend fun getPetData(): NetworkState<List<Pet>> {
        return try {
            val response: List<Pet> = client.get{ url(ApiRoutes.BREEDS)}.body()
            NetworkState.Success(response)
        }
        catch (e: Exception) {
            generateErrorMessage()
        }
    }
}