package learning.android.domain.repositories

import learning.android.domain.models.response.UiBreedModel
import learning.android.domain.models.state.LocalResult

/**
 * The abstraction of the repository pattern (local repo)
 */
interface LocalRepo {
    suspend fun insertBreed(breedModel: UiBreedModel)

    suspend fun getBreeds(): LocalResult<List<UiBreedModel>>

    suspend fun getBreedDetails(id: Int): LocalResult<UiBreedModel>
}