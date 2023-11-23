package learning.android.kmm.network.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Pet(
    @SerialName("id")
    val id: String? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("temperament")
    val temperament: String? = null,

    @SerialName("reference_image_id")
    val photo: String? = null
)