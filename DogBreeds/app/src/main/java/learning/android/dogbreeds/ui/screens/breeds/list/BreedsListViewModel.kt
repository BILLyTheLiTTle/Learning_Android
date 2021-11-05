package learning.android.dogbreeds.ui.screens.breeds.list

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import learning.android.domain.models.response.UiBreedModel
import learning.android.domain.usecases.GetBreedsUseCase
import javax.inject.Inject

@HiltViewModel
class BreedsListViewModel @Inject constructor(
    private val breedsUseCase: GetBreedsUseCase
) : ViewModel() {
    private val TAG = BreedsListViewModel::class.simpleName

    init {
        breedsUseCase.breedsRequest = breedsUseCase.breedsRequest.copy(10, BreedSource.FIRST_PAGE)
//        getBreeds()
    }

    val breedsResult: Flow<PagingData<UiBreedModel>> = Pager(PagingConfig(pageSize = breedsUseCase.breedsRequest.limit)) {
        BreedSource(breedsUseCase)
    }.flow

    /*
    Below, we have 2 options to request data from UI and parse them to UI. Every single option has its
    advantages and disadvantages.

    OPTION 1
    + Cleaner call due to the function
    + Better handling (logging, etc) in the function
    - More code
    - Need of 2 extra variables which will be using for updating the UI

    OPTION 2
    + Much more declarative
    + Direct usage by UI
    - (a) In case we are working on a backing property so you cannot run the code to fetch new data
    - (b) In case we are working on a custom get for the property we cannot stop getting new data
    Obviously, there could be a way to fix (a), (b) but we should stick to KISS principle!
     */
    // OPTION 1
//    private val _breedsResult: MutableStateFlow<NetworkResult<List<UiBreedModel>>> = MutableStateFlow(NetworkResult.loading())
//    val breedsResult = _breedsResult.asStateFlow()
//    private fun getBreeds() {
//        val FUNCTION_TAG = "${TAG}_${::getBreeds.name}"
//        val coroutineName = CoroutineName(FUNCTION_TAG)
//
//        viewModelScope.launch(coroutineName) {
//            Log.d(FUNCTION_TAG, "Coroutine -> ${Thread.currentThread().name}")
//            breedsUseCase.execute()
//                .catch {
//                    _breedsResult.value = NetworkResult.error("Error fetching data")
//                    Log.e(FUNCTION_TAG, it.stackTraceToString())
//                }
//                .collect{
//                    _breedsResult.value = NetworkResult.success(it.data)
//                    Log.d(FUNCTION_TAG, it.data.toString())
//                }
//        }
//    }

    // OPTION 2
//    val breedsResult: StateFlow<NetworkResult<List<UiBreedModel>>> =
//        breedsUseCase.execute()
//            .catch {
//                emit(NetworkResult.error("Error fetching data"))
//                Log.e("ERROR", it.stackTraceToString())
//            }
//            .stateIn(viewModelScope, SharingStarted.Lazily, NetworkResult.loading())
}