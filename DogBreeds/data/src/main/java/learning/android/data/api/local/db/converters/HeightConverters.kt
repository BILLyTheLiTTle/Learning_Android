package learning.android.data.api.local.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import learning.android.data.models.BreedModel

/**
 * Convert BreedModel.Height to JSON and reverse to store value to DB
 */
class HeightConverters {
    /**
     * Convert a BreedModel.Height to JSON
     */
    @TypeConverter
    fun toHeightJson(height: BreedModel.Height): String {
        return Gson().toJson(height)
    }

    /**
     * Convert JSON to BreedModel.Height
     */
    @TypeConverter
    fun toHeightObject(jsonHeight: String): BreedModel.Height {
        val notesType = object : TypeToken<BreedModel.Height>() {}.type
        return Gson().fromJson<BreedModel.Height>(jsonHeight, notesType)
    }
}