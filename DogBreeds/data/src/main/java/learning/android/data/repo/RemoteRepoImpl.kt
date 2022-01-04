package learning.android.data.repo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import learning.android.data.mappers.BreedMapper
import learning.android.data.api.network.ApiService
import learning.android.domain.models.state.NetworkResult
import learning.android.domain.models.response.UiBreedModel
import learning.android.domain.repositories.RemoteRepo
import learning.android.domain.utils.coLog
import learning.android.domain.utils.erLog
import javax.inject.Inject

/**
 * The implementation of the repository pattern (remote repo)
 */
class RemoteRepoImpl @Inject constructor(
    private val apiService: ApiService,
    private val breedMapper: BreedMapper
) : RemoteRepo {

    private val TAG = RemoteRepoImpl::class.simpleName

    override suspend fun getBreeds(limit: Int, page: Int): NetworkResult<List<UiBreedModel>> {
        return try {
            coLog(TAG, ::getBreeds.name)
            val breeds = withContext(Dispatchers.IO) {
                apiService.getBreeds(limit, page)
                    .map { breedMapper.toUiBreedModel(it) }
            }
            NetworkResult.success(breeds)
        } catch (e: Exception) {
            erLog(TAG, ::getBreedDetails.name, e)
            NetworkResult.error(e)
        }
    }

    override suspend fun getBreedDetails(id: Int): NetworkResult<UiBreedModel> {
        return try {
            coLog(TAG, ::getBreedDetails.name)

            val breed = withContext(Dispatchers.IO) {
                apiService.getBreedDetails(id)
            }
            val image = withContext(Dispatchers.IO) {
                apiService.getBreedImage(breed.referenceImageId)
            }

            val uiBreed = breedMapper.toUiBreedModel(breed)
            val uiImage = breedMapper.toUiBreedImage(image)
            val breedFull = uiBreed.copy(image = uiImage)

            NetworkResult.success(breedFull)
        } catch (e: Exception) {
            erLog(TAG, ::getBreedDetails.name, e)
            NetworkResult.error(e)
        }
    }

    override suspend fun getBreedDetailsAsFlow(id: Int): Flow<NetworkResult<UiBreedModel>> {
        return flow<NetworkResult<UiBreedModel>> {
            val breed = apiService.getBreedDetails(id)
            val uiBreed = breedMapper.toUiBreedModel(breed)
            emit(NetworkResult.success(uiBreed))
        }.flowOn(Dispatchers.IO)
    }
}