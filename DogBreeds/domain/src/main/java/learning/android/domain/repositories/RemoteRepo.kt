package learning.android.domain.repositories

import kotlinx.coroutines.flow.Flow
import learning.android.domain.models.flow.NetworkResult
import learning.android.domain.models.response.UiBreedModel

/**
 * The abstraction of the repository pattern
 */
interface RemoteRepo {
    fun getBreeds(limit: Int, page: Int): Flow<NetworkResult<List<UiBreedModel>>>
}