package learning.android.data.repo

import android.util.Log
import learning.android.data.mappers.BreedMapper
import learning.android.data.network.ApiService
import learning.android.domain.models.flow.NetworkResult
import learning.android.domain.models.response.UiBreedModel
import learning.android.domain.repositories.RemoteRepo
import javax.inject.Inject

/**
 * The implementation of the repository pattern
 */
class RemoteRepoImpl @Inject constructor(
    private val apiService: ApiService,
    private val breedMapper: dagger.Lazy<BreedMapper>
) : RemoteRepo {

    private val TAG = RemoteRepoImpl::class.simpleName

    override suspend fun getBreeds(limit: Int, page: Int): NetworkResult<List<UiBreedModel>> {
        val FUNCTION_TAG = "${TAG}_${::getBreeds.name}"

        return try {
            Log.d(FUNCTION_TAG, "Coroutine -> ${Thread.currentThread().name}")
            val breeds = apiService.getBreeds(limit, page)
                .map { breedMapper.get().toUiBreedModel(it) }
            NetworkResult.success(breeds)
        } catch (e: Exception) {
            NetworkResult.error(e)
        }
    }
}