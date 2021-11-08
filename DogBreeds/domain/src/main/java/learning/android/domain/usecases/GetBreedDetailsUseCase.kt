package learning.android.domain.usecases

import learning.android.domain.models.state.NetworkResult
import learning.android.domain.models.response.UiBreedModel
import learning.android.domain.repositories.RemoteRepo
import javax.inject.Inject

class GetBreedDetailsUseCase @Inject constructor(
    var id: Int,
    private val apiRepo: RemoteRepo
): UseCase<NetworkResult<UiBreedModel>>
{
    override suspend fun execute(): NetworkResult<UiBreedModel> =
        apiRepo.getBreedDetails(id)
}