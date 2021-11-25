package learning.android.dogbreeds.ui.screens.breeds.list

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import learning.android.domain.models.response.UiBreedModel
import learning.android.domain.models.state.DataResult
import learning.android.domain.models.state.Status
import learning.android.domain.usecases.GetBreedsUseCase
import kotlin.system.measureTimeMillis

class BreedSource(
    private val breedsUseCase: GetBreedsUseCase
): PagingSource<Int, UiBreedModel>() {

    init {
        resetProperties()
    }

    private fun resetProperties() {
        breedsUseCase.shouldGetLocalData = true
        breedsUseCase.breedsRequest = breedsUseCase.breedsRequest.copy(page = FIRST_PAGE)
    }

    override fun getRefreshKey(state: PagingState<Int, UiBreedModel>): Int? {
        _isFetchingState.value = true
        /*
        When I return the state.anchorPosition the refresh was starting over AND from the start.
        For example, if anchorPosition was 25 the request contained nextPage = 25, then 24, 26, 23, 27...
        When the decreasing values arrived to 1 (never back to 0) it keeps loading and increasing values!
        This was visible to the user as the list was loaded bottom-up!
         */
//        return state.anchorPosition
        return FIRST_PAGE
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UiBreedModel> {
        return try {
            _isFetchingState.value = true
            val nextPage = params.key ?: FIRST_PAGE
            var breedListResponse: DataResult<List<UiBreedModel>>
            val time = measureTimeMillis {
//                withContext(Dispatchers.Default) { // No need to do this for this example
                    breedListResponse = breedsUseCase.execute()
//                }
            }
            Log.d("Breeds List Performance", "$time")

            breedsUseCase.breedsRequest = breedsUseCase.breedsRequest.copy(page = nextPage + 1)

            _isFetchingState.value = false

            if (breedListResponse.status == Status.ERROR) {
                LoadResult.Error(breedListResponse.error ?: Throwable("Error"))
            } else {
                LoadResult.Page(
                    data = breedListResponse.data ?: emptyList(),
                    prevKey = if (nextPage == FIRST_PAGE)
                        null
                    else
                        nextPage - 1,
                    nextKey = breedsUseCase.breedsRequest.page
                )
            }
        } catch (e: Exception) {
            _isFetchingState.value = false
            LoadResult.Error(e)
        }
    }

    companion object {
        const val FIRST_PAGE = 0

        /*
        Create a fetching state from the PagingSource, to use across the app.
        It is a good (IMHO) option to handle the refresh actions
         */
        private val _isFetchingState: MutableStateFlow<Boolean> = MutableStateFlow(false)
        val isFetchingState = _isFetchingState.asStateFlow()
    }
}