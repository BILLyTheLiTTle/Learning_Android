package learning.android.kmm.network.settings

import kotlinx.serialization.json.Json

class JsonSerializerSetup {
    val jsonSettings = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = false
    }
}