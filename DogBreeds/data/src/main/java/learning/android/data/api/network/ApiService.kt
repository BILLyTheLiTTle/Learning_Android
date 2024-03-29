package learning.android.data.api.network

import learning.android.data.models.BreedModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * The API calls for usage with Retrofit
 */
interface ApiService {

    @GET("breeds")
    suspend fun getBreeds(@Query("limit") limit: Int, @Query("page") page: Int): List<BreedModel>

    @GET("breeds/{breed_id}")
    suspend fun getBreedDetails(@Path("breed_id") breedId: Int): BreedModel

    @GET("images/{image_id}")
    suspend fun getBreedImage(@Path("image_id") breedId: String): BreedModel.Image
}