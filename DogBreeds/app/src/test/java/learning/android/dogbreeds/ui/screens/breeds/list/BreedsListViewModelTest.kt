package learning.android.dogbreeds.ui.screens.breeds.list

import androidx.paging.PagingSource
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import learning.android.domain.models.response.UiBreedModel
import learning.android.domain.models.state.DataResult
import learning.android.domain.usecases.GetBreedsUseCase
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class BreedsListViewModelTest {

    private lateinit var useCase: GetBreedsUseCase

    lateinit var breedsPagingSource: BreedSource

    @Before
    fun setup() {
        useCase = mockk(relaxed = true)
        every { useCase.shouldGetLocalData = false } returns Unit
        every {
            useCase.breedsRequest = useCase.breedsRequest.copy(page = BreedSource.FIRST_PAGE)
        } returns Unit
        breedsPagingSource = BreedSource(useCase)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_whenFetchItemsWithError_shouldPagerContainsNetworkError() {
        runBlockingTest {
            coEvery { useCase.execute() } returns DataResult.error(Exception("Exception msg"))

            val actual = breedsPagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = 0,
                    loadSize = 30,
                    placeholdersEnabled = true
                )
            )
            val expected = PagingSource.LoadResult.Error<Any, Any>(Exception("Exception msg"))

            Assert.assertEquals(true, actual is PagingSource.LoadResult.Error)
            Assert.assertEquals(
                expected.throwable.message,
                (actual as PagingSource.LoadResult.Error).throwable.message
            )
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_whenFetchItemsWithSuccess_shouldPagerContainsItems() {
        runBlockingTest {
            // TODO try using Hierarchical mocking for this item
            val item = UiBreedModel(
                weight = UiBreedModel.UiWeight(""), height = UiBreedModel.UiHeight(""),
                id = 1, name = "test", lifeSpan = "", temperament = "", origin = "",
                image = null, countryCode = null, description = null
            )
            coEvery { useCase.execute() } returns DataResult.success(listOf(item))

            val actual = breedsPagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = 0,
                    loadSize = 30,
                    placeholdersEnabled = true
                )
            )
            val expected = PagingSource.LoadResult.Page(
                data = listOf(item),
                prevKey = null,
                nextKey = 1
            )

            Assert.assertEquals(true, actual is PagingSource.LoadResult.Page)
            Assert.assertEquals(
                expected.data[0].id,
                (actual as PagingSource.LoadResult.Page).data[0].id
            )
        }
    }
}