package learning.android.data.api.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import learning.android.data.models.BreedModel

/**
 * The API calls for usage with Room
 */
@Dao
interface BreedDao {
    @Query("SELECT * from my_breed_list")
    fun getAll(): List<BreedModel>

    @Query("SELECT * from my_breed_list where id = :id")
    fun getById(id: Int) : BreedModel?

    @Insert
    suspend fun insert(item:BreedModel)

    @Delete
    suspend fun delete(item:BreedModel)

    @Query("DELETE FROM my_breed_list")
    suspend fun deleteAll()
}