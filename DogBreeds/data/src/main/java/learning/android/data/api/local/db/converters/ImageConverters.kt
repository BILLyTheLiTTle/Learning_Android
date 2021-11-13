package learning.android.data.api.local.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import learning.android.data.models.BreedModel

/**
 * Convert BreedModel.Image to JSON and reverse to store value to DB
 */
class ImageConverters {
    /**
     * Convert a BreedModel.Image to JSON
     */
    @TypeConverter
    fun toImageJson(image: BreedModel.Image): String {
        return Gson().toJson(image)
    }

    /**
     * Convert a JSON to BreedModel.Image
     */
    @TypeConverter
    fun toImageObject(jsonImage: String): BreedModel.Image {
        val notesType = object : TypeToken<BreedModel.Image>() {}.type
        return Gson().fromJson(jsonImage, notesType)
    }
}