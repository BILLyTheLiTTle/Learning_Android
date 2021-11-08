package learning.android.dogbreeds.ui.screens.breeds.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import learning.android.domain.models.flow.NetworkResult
import learning.android.domain.models.response.UiBreedModel
import learning.android.domain.usecases.GetBreedDetailsUseCase
import learning.android.domain.utils.coDebug
import learning.android.domain.utils.erDebug
import javax.inject.Inject

@HiltViewModel
class BreedDetailsViewModel @Inject constructor(
    private val breedDetailsUseCase: GetBreedDetailsUseCase
) : ViewModel() {

    private val TAG = BreedDetailsViewModel::class.simpleName

    /*
    Below, we have 2 options to request data from UI and parse them to UI
     */

    /* OPTION 1
    + Cleaner call to the API:
    (a) -> Just call a function and get its value. No need to change any API when the StateFlow will no be with us in the future!
    + Better handling (logging, etc) in the function due to the small body of the coroutine function
    (b) -> Just 2 lines of code
    - No Reactive Trend applied to the core functionality
     */
    private val _breedDetailsResult: MutableStateFlow<NetworkResult<UiBreedModel>> = MutableStateFlow(NetworkResult.loading())
    val breedDetailsResult = _breedDetailsResult.asStateFlow()
    fun getBreedDetails(id: String) {
        val FUNCTION_TAG = "${TAG}_${::getBreedDetails.name}"
        val coroutineName = CoroutineName(FUNCTION_TAG)

        breedDetailsUseCase.id = id.toInt()

        viewModelScope.launch(coroutineName) {
            coDebug(coroutineName) //(b)
            _breedDetailsResult.value = breedDetailsUseCase.execute() //(a) //(b)
        }
    }

    /* OPTION 2
    + Much more declarative
    (a) -> Use Reactive from bottom-up
    - Bigger body of the coroutine function

    WARNING: In order to make it work you need to change "GetBreedDetailsUseCase"
     * From "UseCase<NetworkResult<UiBreedModel>> to  UseCase<Flow<NetworkResult<UiBreedModel>>>
     * From "execute(): NetworkResult<UiBreedModel>" to "execute(): Flow<NetworkResult<UiBreedModel>>"
     */
//    private val _breedDetailsResult: MutableStateFlow<NetworkResult<UiBreedModel>> = MutableStateFlow(NetworkResult.loading())
//    val breedDetailsResult = _breedDetailsResult.asStateFlow()
//    fun getBreedDetails(id: String) {
//        val FUNCTION_TAG = "${TAG}_${::getBreedDetails.name}"
//        val coroutineName = CoroutineName(FUNCTION_TAG)
//
//        breedDetailsUseCase.id = id.toInt()
//
//        viewModelScope.launch(coroutineName) {
//            coDebug(coroutineName)
//            breedDetailsUseCase.execute() // (a)
//                .catch {
//                    _breedDetailsResult.value = NetworkResult.error("Error fetching data")
//                    erDebug(TAG, ::getBreedDetails.name, it.error)
//                }
//                .collect{
//                    _breedDetailsResult.value = NetworkResult.success(it.data)
//                    Log.d(FUNCTION_TAG, it.data.toString())
//                }
//        }
//    }
}