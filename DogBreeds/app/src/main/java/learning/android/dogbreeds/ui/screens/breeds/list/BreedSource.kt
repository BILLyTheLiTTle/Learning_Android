package learning.android.dogbreeds.ui.screens.breeds.list

import androidx.paging.PagingSource
import androidx.paging.PagingState
import learning.android.domain.models.flow.NetworkResult
import learning.android.domain.models.response.UiBreedModel
import learning.android.domain.usecases.GetBreedsUseCase

class BreedSource(
    private val breedsUseCase: GetBreedsUseCase
): PagingSource<Int, UiBreedModel>() {

    init {
        breedsUseCase.breedsRequest = breedsUseCase.breedsRequest.copy(page = FIRST_PAGE)
    }

    override fun getRefreshKey(state: PagingState<Int, UiBreedModel>): Int? {
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
            val nextPage = params.key ?: FIRST_PAGE
            val breedListResponse = breedsUseCase.execute()

            breedsUseCase.breedsRequest = breedsUseCase.breedsRequest.copy(page = nextPage + 1)

            if (breedListResponse.status == NetworkResult.Status.ERROR) {
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
            LoadResult.Error(e)
        }
    }

    companion object {
        const val FIRST_PAGE = 0
    }
}