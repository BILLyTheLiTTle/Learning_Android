package learning.android.data.api.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import learning.android.data.api.local.db.converters.HeightConverters
import learning.android.data.api.local.db.converters.ImageConverters
import learning.android.data.api.local.db.converters.WeightConverters
import learning.android.data.models.BreedModel

@Database(entities = [BreedModel::class], version = 1)
@TypeConverters(WeightConverters:: class, HeightConverters::class, ImageConverters::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun breedDao(): BreedDao
}