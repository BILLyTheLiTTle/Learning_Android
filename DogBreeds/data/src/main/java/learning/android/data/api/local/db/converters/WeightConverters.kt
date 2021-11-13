package learning.android.data.api.local.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import learning.android.data.models.BreedModel

/**
 * Convert BreedModel.Weight to JSON and reverse to store value to DB
 */
class WeightConverters {
    /**
     * Convert a BreedModel.Weight to JSON
     */
    @TypeConverter
    fun toWeightJson(weight: BreedModel.Weight): String {
        return Gson().toJson(weight)
    }

    /**
     * Convert JSON to BreedModel.Weight
     */
    @TypeConverter
    fun toWeightObject(jsonWeight: String): BreedModel.Weight {
        val notesType = object : TypeToken<BreedModel.Weight>() {}.type
        return Gson().fromJson<BreedModel.Weight>(jsonWeight, notesType)
    }
}