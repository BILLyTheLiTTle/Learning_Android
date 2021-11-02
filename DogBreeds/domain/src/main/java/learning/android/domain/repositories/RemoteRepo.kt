package learning.android.domain.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import learning.android.domain.models.flow.NetworkResult
import learning.android.domain.models.response.UiBreedModel

interface RemoteRepo {
    fun getBreeds(limit: Int, page: Int): Flow<NetworkResult<List<UiBreedModel>>>
}