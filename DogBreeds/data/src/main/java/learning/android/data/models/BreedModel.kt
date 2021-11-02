package learning.android.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The JSON object for breeds.
 */
data class BreedModel(
    @SerializedName("weight")
    @Expose
    val weight: Weight,

    @SerializedName("height")
    @Expose
    val height: Height,

    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("bred_for")
    @Expose
    val bredFor: String,

    @SerializedName("breed_group")
    @Expose
    val breedGroup: String,

    @SerializedName("life_span")
    @Expose
    val lifeSpan: String,

    @SerializedName("temperament")
    @Expose
    val temperament: String,

    @SerializedName("origin")
    @Expose
    val origin: String,

    @SerializedName("reference_image_id")
    @Expose
    val referenceImageId: String,

    @SerializedName("image")
    @Expose
    val image: Image,

    @SerializedName("country_code")
    @Expose
    val countryCode: String,

    @SerializedName("description")
    @Expose
    val description: String,

    @SerializedName("history")
    @Expose
    val history: String) {

    class Height(
        @SerializedName("imperial")
        @Expose
        val imperial: String,

        @SerializedName("metric")
        @Expose
        val metric: String
    )

    class Weight(
        @SerializedName("imperial")
        @Expose
        val imperial: String,

        @SerializedName("metric")
        @Expose
        val metric: String
    )

    class Image(
        @SerializedName("id")
        @Expose
        val id: String,

        @SerializedName("width")
        @Expose
        val width: Int,

        @SerializedName("height")
        @Expose
        val height: Int,

        @SerializedName("url")
        @Expose
        val url: String
    )
}