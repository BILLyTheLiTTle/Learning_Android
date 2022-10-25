package learning.android.kmm.network.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class DogResponse(
    @SerialName("message")
    val message: String? = null,

    @SerialName("status")
    var status: Int? = null
)