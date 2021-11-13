package learning.android.domain.usecases

import kotlinx.coroutines.*
import learning.android.domain.mappers.StateMapper
import learning.android.domain.mergers.StateMerger
import learning.android.domain.models.request.BreedsRequest
import learning.android.domain.models.response.UiBreedModel
import learning.android.domain.models.state.DataResult
import learning.android.domain.repositories.LocalRepo
import learning.android.domain.repositories.RemoteRepo
import javax.inject.Inject

/**
 * The use-case implementation for getting a list of breeds
 */
class GetBreedsUseCase @Inject constructor(
    var breedsRequest: BreedsRequest,
    private val apiRepo: RemoteRepo,
    private val localRepo: LocalRepo,
    private val stateMapper: StateMapper<List<UiBreedModel>>,
    private val stateMerger: StateMerger<List<UiBreedModel>>): UseCase<DataResult<List<UiBreedModel>>>
 {
     /*
     Flag to used in order to retrieve DB data only once
      */
     var shouldGetLocalData = true

     /*
     This piece of code is totally wrong in production code.
     I just entered it here to add data to DB at the first run and then
     this code will be commented out
      */
//     init {
//         GlobalScope.launch {
//             localRepo.insertBreed(
//                 UiBreedModel(
//                     id = 0,
//                     name = "Cerberus",
//                     countryCode = "GR",
//                     description = "Ancient dog of Hades",
//                     lifeSpan = "Forever",
//                     origin = "Greece",
//                     temperament = "Angry",
//                     height = UiBreedModel.UiHeight("200"),
//                     weight = UiBreedModel.UiWeight("100"),
//                     image = UiBreedModel.UiImage("https://media.threatpost.com/wp-content/uploads/sites/103/2019/08/13124059/Cerberus.jpg")
//                 )
//             )
//         }
//     }

     override suspend fun execute(): DataResult<List<UiBreedModel>> {
         val networkResult = apiRepo.getBreeds(breedsRequest.limit, breedsRequest.page)
         val networkDataResult = stateMapper.toDataResult(networkResult)

         return if (shouldGetLocalData) {
             val localDataResult = withContext(Dispatchers.IO) {
                 val localResult = localRepo.getBreeds()
                 stateMapper.toDataResult(localResult)
             }
             shouldGetLocalData = false
             stateMerger.concat(localDataResult, networkDataResult, priority = StateMerger.Priority.DATA)
         } else {
             networkDataResult
         }
     }
}