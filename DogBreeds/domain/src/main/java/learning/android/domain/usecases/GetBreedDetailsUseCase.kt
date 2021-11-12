package learning.android.domain.usecases

import learning.android.domain.mappers.StateMapper
import learning.android.domain.models.response.UiBreedModel
import learning.android.domain.models.state.DataResult
import learning.android.domain.repositories.RemoteRepo
import javax.inject.Inject

class GetBreedDetailsUseCase @Inject constructor(
    var id: Int,
    private val apiRepo: RemoteRepo,
    private val stateMapper: StateMapper<UiBreedModel>
): UseCase<DataResult<UiBreedModel>>
{
    override suspend fun execute(): DataResult<UiBreedModel> {
        val networkResult = apiRepo.getBreedDetails(id)
        return stateMapper.toDataResult(networkResult)
    }
}