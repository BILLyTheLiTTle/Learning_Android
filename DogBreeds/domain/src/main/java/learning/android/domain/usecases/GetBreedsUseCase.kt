package learning.android.domain.usecases

import learning.android.domain.mappers.StateMapper
import learning.android.domain.models.request.BreedsRequest
import learning.android.domain.models.response.UiBreedModel
import learning.android.domain.models.state.DataResult
import learning.android.domain.repositories.RemoteRepo
import javax.inject.Inject

/**
 * The use-case implementation for getting a list of breeds
 */
class GetBreedsUseCase @Inject constructor(
    var breedsRequest: BreedsRequest,
    private val apiRepo: RemoteRepo,
    private val stateMapper: StateMapper<List<UiBreedModel>>): UseCase<DataResult<List<UiBreedModel>>>
 {
     override suspend fun execute(): DataResult<List<UiBreedModel>> {
         val networkResult = apiRepo.getBreeds(breedsRequest.limit, breedsRequest.page)
         return stateMapper.toDataResult(networkResult)
     }
}