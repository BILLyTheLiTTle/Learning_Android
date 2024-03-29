package learning.android.domain.repositories

import kotlinx.coroutines.flow.Flow
import learning.android.domain.models.state.NetworkResult
import learning.android.domain.models.response.UiBreedModel

/**
 * The abstraction of the repository pattern (remote repo)
 */
interface RemoteRepo {
    suspend fun getBreeds(limit: Int, page: Int): NetworkResult<List<UiBreedModel>>

    suspend fun getBreedDetails(id: Int): NetworkResult<UiBreedModel>
    suspend fun getBreedDetailsAsFlow(id: Int): Flow<NetworkResult<UiBreedModel>>
}