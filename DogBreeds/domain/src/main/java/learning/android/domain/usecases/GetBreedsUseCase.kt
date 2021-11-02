package learning.android.domain.usecases

import kotlinx.coroutines.flow.Flow
import learning.android.domain.models.flow.NetworkResult
import learning.android.domain.models.request.BreedsRequest
import learning.android.domain.models.response.UiBreedModel
import learning.android.domain.repositories.RemoteRepo
import javax.inject.Inject

/**
 * The use-case implementation for getting a list of breeds
 */
class GetBreedsUseCase @Inject constructor(
    var breedsRequest: BreedsRequest,
    private val apiRepo: RemoteRepo): UseCase<Flow<NetworkResult<List<UiBreedModel>>>>
 {
     override fun execute(): Flow<NetworkResult<List<UiBreedModel>>> =
         apiRepo.getBreeds(breedsRequest.limit, breedsRequest.page)
}