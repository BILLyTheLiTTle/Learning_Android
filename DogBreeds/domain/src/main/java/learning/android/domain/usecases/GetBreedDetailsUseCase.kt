package learning.android.domain.usecases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import learning.android.domain.mappers.StateMapper
import learning.android.domain.models.response.UiBreedModel
import learning.android.domain.models.state.DataResult
import learning.android.domain.repositories.LocalRepo
import learning.android.domain.repositories.RemoteRepo
import javax.inject.Inject

class GetBreedDetailsUseCase @Inject constructor(
    var id: Int,
    private val apiRepo: RemoteRepo,
    private val localRepo: LocalRepo,
    private val stateMapper: StateMapper<UiBreedModel>
): UseCase<DataResult<UiBreedModel>>
{
    override suspend fun execute(): DataResult<UiBreedModel> {
        return if (id == 0) {
            withContext(Dispatchers.IO) {
                val localResult = localRepo.getBreedDetails(id)
                stateMapper.toDataResult(localResult)
            }
        } else {
            val networkResult = apiRepo.getBreedDetails(id)
            stateMapper.toDataResult(networkResult)
        }

    }
}