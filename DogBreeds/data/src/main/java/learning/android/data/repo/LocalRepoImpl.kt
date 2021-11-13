package learning.android.data.repo

import learning.android.data.api.local.db.BreedDao
import learning.android.data.mappers.BreedMapper
import learning.android.data.models.BreedModel
import learning.android.domain.models.response.UiBreedModel
import learning.android.domain.models.state.LocalResult
import learning.android.domain.repositories.LocalRepo
import learning.android.domain.utils.coLog
import learning.android.domain.utils.erLog
import javax.inject.Inject

/**
 * The implementation of the repository pattern (local repo)
 */
class LocalRepoImpl @Inject constructor(
    private val breedDao: BreedDao,
    private val breedMapper: dagger.Lazy<BreedMapper>
) : LocalRepo {

    private val TAG = LocalRepoImpl::class.simpleName

    override suspend fun getBreeds(): LocalResult<List<UiBreedModel>> {
        return try {
            coLog(TAG, ::getBreeds.name)
            val breeds = breedDao.getAll()
                .map { breedMapper.get().toUiBreedModel(it) }
            LocalResult.success(breeds)
        } catch (e: Exception) {
            erLog(TAG, ::getBreedDetails.name, e)
            LocalResult.error(e)
        }
    }

    override suspend fun getBreedDetails(id: Int): LocalResult<UiBreedModel> {
        return try {
            coLog(TAG, ::getBreedDetails.name)

            val breed = breedDao.getById(id)
            val image = breed?.image ?: BreedModel.Image("", 0, 0, "")

            if (breed == null) {
                throw Exception("No data in DB")
            }
            val uiBreed = breedMapper.get().toUiBreedModel(breed)
            val uiImage = breedMapper.get().toUiBreedImage(image)
            val breedFull = uiBreed.copy(image = uiImage)

            LocalResult.success(breedFull)
        } catch (e: Exception) {
            erLog(TAG, ::getBreedDetails.name, e)
            LocalResult.error(e)
        }
    }

    override suspend fun insertBreed(breedModel: UiBreedModel) {
        breedDao.insert(breedMapper.get().toBreedModel(breedModel))
    }
}