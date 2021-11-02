package learning.android.dogbreeds.ui.screens.breeds.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import learning.android.domain.models.flow.NetworkResult
import learning.android.domain.models.request.BreedsRequest
import learning.android.domain.models.response.UiBreedModel
import learning.android.domain.usecases.GetBreedsUseCase
import javax.inject.Inject

@HiltViewModel
class BreedsListViewModel @Inject constructor(
    private val breedsUseCase: GetBreedsUseCase
) : ViewModel() {
    private val TAG = BreedsListViewModel::class.simpleName

    init {
        breedsUseCase.breedsRequest = breedsUseCase.breedsRequest.copy(10, 0)
        getBreeds()
    }

    private val _breedsResult: MutableStateFlow<NetworkResult<List<UiBreedModel>>> = MutableStateFlow(NetworkResult.loading())
    val breedsResult = _breedsResult.asStateFlow()
    private fun getBreeds() {
        val FUNCTION_TAG = "${TAG}_${::getBreeds.name}"
        val coroutineName = CoroutineName(FUNCTION_TAG)

        viewModelScope.launch(coroutineName) {
            Log.d(FUNCTION_TAG, "Coroutine -> ${Thread.currentThread().name}")
            breedsUseCase.execute()
                .catch {
                    _breedsResult.value = NetworkResult.error("Error fetching data")
                    Log.e(FUNCTION_TAG, it.stackTraceToString())
                }
                .collect{
                    _breedsResult.value = NetworkResult.success(it.data)
                    Log.d(FUNCTION_TAG, it.data.toString())
                }
        }
    }

//    val breedsResult: StateFlow<NetworkResult<List<UiBreedModel>>> =
//        breedsUseCase.execute()
//            .catch {
//                emit(NetworkResult.error("Error fetching data"))
//                Log.e("ERROR", it.stackTraceToString())
//            }
//            .stateIn(viewModelScope, SharingStarted.Lazily, NetworkResult.loading())
}