package learning.android.data.models

import androidx.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import learning.android.data.api.local.db.converters.HeightConverters
import learning.android.data.api.local.db.converters.ImageConverters
import learning.android.data.api.local.db.converters.WeightConverters

/**
 * The JSON and DB object for breeds.
 */
@Entity(tableName = "my_breed_list")
data class BreedModel(
    @ColumnInfo(name = "weight")
    @TypeConverters(WeightConverters::class)
    @SerializedName("weight")
    @Expose
    val weight: Weight,

    @ColumnInfo(name = "height")
    @TypeConverters(HeightConverters::class)
    @SerializedName("height")
    @Expose
    val height: Height,

    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    @Expose
    val id: Int,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    val name: String = "",

    @ColumnInfo(name = "bred_for")
    @SerializedName("bred_for")
    @Expose
    val bredFor: String = "",

    @ColumnInfo(name = "breed_group")
    @SerializedName("breed_group")
    @Expose
    val breedGroup: String = "",

    @ColumnInfo(name = "life_span")
    @SerializedName("life_span")
    @Expose
    val lifeSpan: String = "",

    @ColumnInfo(name = "temperament")
    @SerializedName("temperament")
    @Expose
    val temperament: String = "",

    @ColumnInfo(name = "origin")
    @SerializedName("origin")
    @Expose
    val origin: String = "",

    @ColumnInfo(name = "reference_image_id")
    @SerializedName("reference_image_id")
    @Expose
    val referenceImageId: String = "",

    @ColumnInfo(name = "image")
    @TypeConverters(ImageConverters::class)
    @SerializedName("image")
    @Expose
    val image: Image,

    @ColumnInfo(name = "country_code")
    @SerializedName("country_code")
    @Expose
    val countryCode: String = "",

    @ColumnInfo(name = "description")
    @SerializedName("description")
    @Expose
    val description: String = "",

    @ColumnInfo(name = "history")
    @SerializedName("history")
    @Expose
    val history: String = "") {

    class Height(
        @ColumnInfo(name = "imperial")
        @SerializedName("imperial")
        @Expose
        val imperial: String = "",

        @ColumnInfo(name = "metric")
        @SerializedName("metric")
        @Expose
        val metric: String = ""
    )

    class Weight(
        @ColumnInfo(name = "imperial")
        @SerializedName("imperial")
        @Expose
        val imperial: String = "",

        @ColumnInfo(name = "metric")
        @SerializedName("metric")
        @Expose
        val metric: String = ""
    )

    class Image(
        @ColumnInfo(name = "id")
        @SerializedName("id")
        @Expose
        val id: String = "",

        @ColumnInfo(name = "width")
        @SerializedName("width")
        @Expose
        val width: Int = 0,

        @ColumnInfo(name = "height")
        @SerializedName("height")
        @Expose
        val height: Int = 0,

        @ColumnInfo(name = "url")
        @SerializedName("url")
        @Expose
        val url: String = ""
    )
}