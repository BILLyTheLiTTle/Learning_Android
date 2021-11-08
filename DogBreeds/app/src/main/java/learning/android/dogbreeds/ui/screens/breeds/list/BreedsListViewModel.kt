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
    }

    val breedsResult: Flow<PagingData<UiBreedModel>> = Pager(PagingConfig(pageSize = breedsUseCase.breedsRequest.limit)) {
        BreedSource(breedsUseCase)
    }.flow
}