package learning.android.dogbreeds.ui.screens.breeds.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import learning.android.domain.models.response.UiBreedModel
import learning.android.domain.models.state.DataResult
import learning.android.domain.usecases.GetBreedDetailsUseCase
import learning.android.domain.utils.coLog
import javax.inject.Inject
import kotlin.system.measureTimeMillis

@HiltViewModel
class BreedDetailsViewModel @Inject constructor(
    private val breedDetailsUseCase: GetBreedDetailsUseCase
) : ViewModel() {

    private val TAG = BreedDetailsViewModel::class.simpleName

    /*
    I could have navigate to previous/next breed by using:
    - getBreedDetails() function directly by the composables
    - LaunchedEffect() in composable by using and changing its parameter
        (parameter should be something with state instead of Unit obviously)
    - SharedFlow and write much more code(!)
     */
    private val _event: MutableSharedFlow<UserActionEvent<Int>> = MutableSharedFlow()

    init {
        viewModelScope.launch {
            _event.onEach { handleUserAction(it) }. collect()
        }
    }

    fun setUserAction(action: UserActionEvent<Int>) {
        viewModelScope.launch {
            _event.emit(action)
        }
    }

    private fun handleUserAction(action: UserActionEvent<Int>) {
        /*
        Obviously, I don't need this when clause but I would like to see
        how it looks when I have a lot of events!
         */
        when (action) {
            is NextBreed<Int> -> {
                getBreedDetails(action.value.toString())
            }
            is PreviousBreed<Int> -> {
                getBreedDetails(action.value.toString())
            }
            else -> {

            }
        }
    }

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
    private val _breedDetailsResult: MutableStateFlow<DataResult<UiBreedModel>> = MutableStateFlow(DataResult.loading())
    val breedDetailsResult = _breedDetailsResult.asStateFlow()
    fun getBreedDetails(id: String) {
        val FUNCTION_TAG = "${TAG}_${::getBreedDetails.name}"
        val coroutineName = CoroutineName(FUNCTION_TAG)

        breedDetailsUseCase.id = id.toInt()

        val time = measureTimeMillis {
            viewModelScope.launch(coroutineName) {
                coLog(coroutineName) //(b)
                _breedDetailsResult.value = breedDetailsUseCase.execute() //(a) //(b)
            }
        }
        Log.d("Breed Detail Performanc", "$time")
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
//            coLog(coroutineName)
//            breedDetailsUseCase.execute() // (a)
//                .catch {
//                    _breedDetailsResult.value = NetworkResult.error("Error fetching data")
//                    erLog(TAG, ::getBreedDetails.name, it.error)
//                }
//                .collect{
//                    _breedDetailsResult.value = NetworkResult.success(it.data)
//                    Log.d(FUNCTION_TAG, it.data.toString())
//                }
//        }
//    }

    companion object {
        const val FIRST_ITEM_ID = 1 -1 // -1 Due to Cerberus which entered later!
        const val LAST_ITEM_ID = 264
    }
}