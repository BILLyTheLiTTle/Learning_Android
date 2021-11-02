package learning.android.dogbreeds.ui.screens.breeds.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
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

    init {
        breedsUseCase.breedsRequest.limit = "10"
        breedsUseCase.breedsRequest.page = "0"
    }

    private val _breedsResult: MutableStateFlow<NetworkResult<List<UiBreedModel>>> = MutableStateFlow(NetworkResult.loading())
    val breedsResult: StateFlow<NetworkResult<List<UiBreedModel>>> = _breedsResult
    fun getBreeds() {
        viewModelScope.launch {
            breedsUseCase.execute()
                .catch {
                    _breedsResult.value = NetworkResult.error("Error fetching data")
//                    Log.e(this@BreedsListViewModel::getBreeds.name, it.stackTraceToString())
                }
                .collect{
                    _breedsResult.value = NetworkResult.success(it.data)
                }
        }
    }
}