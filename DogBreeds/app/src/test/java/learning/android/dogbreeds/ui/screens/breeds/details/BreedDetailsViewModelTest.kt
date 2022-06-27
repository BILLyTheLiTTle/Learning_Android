package learning.android.dogbreeds.ui.screens.breeds.details

import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.*
import learning.android.domain.models.response.UiBreedModel
import learning.android.domain.models.state.DataResult
import learning.android.domain.models.state.Status
import learning.android.domain.usecases.GetBreedDetailsUseCase
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.lang.Exception


class BreedDetailsViewModelTest {

    private lateinit var useCase: GetBreedDetailsUseCase
    private lateinit var viewModel: BreedDetailsViewModel

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()
    @ExperimentalCoroutinesApi
    private val testScope = TestCoroutineScope(testDispatcher)

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        useCase = mockk()
        viewModel = BreedDetailsViewModel(useCase)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        // Reset Coroutine Dispatcher and Scope.
        testDispatcher.cleanupTestCoroutines()
        testScope.cleanupTestCoroutines()
        unmockkAll()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_whenFetchDataWithError_shouldBeDataWithErrorStatus() {
        testDispatcher.runBlockingTest {
            every { useCase.id = 0 } returns Unit
            coEvery { useCase.execute() } returns DataResult.error(Exception("Exception msg"))

            val results = mutableListOf<DataResult<UiBreedModel>>()
            val job = launch {
                viewModel.breedDetailsResult.toList(results)
            }
            viewModel.getBreedDetails("0")

            Assert.assertEquals(results[0].status, Status.LOADING)
            Assert.assertEquals(results[1].status, Status.ERROR)
            Assert.assertEquals(results[1].error?.message, "Exception msg")

            job.cancel()
        }
    }

    /*
    Unit testing with Coroutines testing v. 1.6.X
    */

    @ExperimentalCoroutinesApi
    @Test
    fun test_whenFetchDataWithError_shouldBeDataWithErrorStatus_v2a() {
        runTest(/*UnconfinedTestDispatcher(/*testScheduler*/)*/) {
            every { useCase.id = 0 } returns Unit
            coEvery { useCase.execute() } returns DataResult.error(Exception("Exception msg"))

            val results = mutableListOf<DataResult<UiBreedModel>>()
            val job = launch(UnconfinedTestDispatcher(/*testScheduler*/)) {
                viewModel.breedDetailsResult.toList(results)
            }
            viewModel.getBreedDetails("0")

            Assert.assertEquals(results[0].status, Status.LOADING)
            Assert.assertEquals(results[1].status, Status.ERROR)
            Assert.assertEquals(results[1].error?.message, "Exception msg")

            job.cancel()
        }
    }

    // Comment/Uncomment the solutions below to experiment.
    @ExperimentalCoroutinesApi
    @Test
    fun test_whenFetchDataWithError_shouldBeDataWithErrorStatus_v2b() {
        runTest {
            every { useCase.id = 0 } returns Unit
            coEvery { useCase.execute() } returns DataResult.error(Exception("Exception msg"))

            val results = mutableListOf<DataResult<UiBreedModel>>()
            val job = launch {
                viewModel.breedDetailsResult.toList(results)
            }
            runCurrent() // Solution 2b_1
//            yield() // Solution 2b_2
//            advanceUntilIdle() // Solution 2b_3

            viewModel.getBreedDetails("0")

            runCurrent() // Solution 2b_1
//            yield() // Solution 2b_2
//            advanceUntilIdle() // Solution 2b_3

            Assert.assertEquals(results[0].status, Status.LOADING)
            Assert.assertEquals(results[1].status, Status.ERROR)
            Assert.assertEquals(results[1].error?.message, "Exception msg")

            job.cancel()
        }
    }
}