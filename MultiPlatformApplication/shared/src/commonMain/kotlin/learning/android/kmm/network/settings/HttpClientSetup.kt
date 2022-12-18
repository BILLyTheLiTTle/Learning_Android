package learning.android.kmm.network.settings

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

class HttpClientSetup(private val jsonSerializerSetup: JsonSerializerSetup) {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(jsonSerializerSetup.jsonSettings)
        }
        engine {
            this.threadsCount = 1
        }
    }
}