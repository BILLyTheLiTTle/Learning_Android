package learning.android.data.repo

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import learning.android.data.mappers.BreedMapper
import learning.android.data.models.BreedModel
import learning.android.data.network.ApiService
import learning.android.domain.models.flow.NetworkResult
import learning.android.domain.models.response.UiBreedModel
import learning.android.domain.repositories.RemoteRepo
import java.lang.Exception
import javax.inject.Inject

class RemoteRepoImpl @Inject constructor(
    private val apiService: ApiService,
    private val breedMapper: dagger.Lazy<BreedMapper>
) : RemoteRepo {

    private val TAG = RemoteRepoImpl::class.simpleName

    override fun getBreeds(limit: Int, page: Int): Flow<NetworkResult<List<UiBreedModel>>> {
        val FUNCTION_TAG = "${TAG}_${::getBreeds.name}"
        return flow {
            Log.d(FUNCTION_TAG, "Coroutine -> ${Thread.currentThread().name}")
            val breeds = apiService.getBreeds(limit, page)
                .map { breedMapper.get().toUiBreedModel(it)
                }
            emit(NetworkResult.success(breeds))
        }.flowOn(Dispatchers.IO)
    }
}