package learning.android.kmm.network.settings

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

object HttpClientSetup {
    val client = HttpClient {
        install(ContentNegotiation) {
            json(JsonSerializerSetup.jsonSettings)
        }
    }
}