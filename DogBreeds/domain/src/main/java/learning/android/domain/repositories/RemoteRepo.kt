package learning.android.domain.repositories

import learning.android.domain.models.flow.NetworkResult
import learning.android.domain.models.response.UiBreedModel

/**
 * The abstraction of the repository pattern
 */
interface RemoteRepo {
    suspend fun getBreeds(limit: Int, page: Int): NetworkResult<List<UiBreedModel>>
}