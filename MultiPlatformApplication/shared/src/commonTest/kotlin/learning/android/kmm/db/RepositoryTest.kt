package learning.android.kmm.db

//import io.mockative.*
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import learning.android.kmm.di.DatabaseModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.MockProvider
import org.koin.test.mock.declareMock
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class RepositoryTest: KoinTest {

    private val repositoryUT: Repository by inject()

    // Mockative attempt 1
//    @Mock
//    internal val mockedDb = mock(classOf<Database>())

    @BeforeTest
    fun setUp() {
        startKoin {
            modules(
                DatabaseModules.databaseModule
            )
            // Mockative attempt 1
//            module {
//                single { mockedDb }
//            }
        }

        // Mockative attempt 2
//        MockProvider.register {
//            mock(it)
//        }
//        declareMock<Database> {
//            given(this).function(this::selectItem).whenInvokedWith(any<Long>()).then { flowOf("Result") }
//        }

        // Mockative attempt 1
//        given(mockedDb).function(mockedDb::selectItem).whenInvokedWith(any<Long>()).then { flowOf("Result") }

        MockProvider.register {
            mockkClass(it)
        }
        declareMock<Database> {
            every { selectItem(any()) } returns(flowOf("Result"))
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getData_dummyTest() {
        runTest {
            val resultList = mutableListOf<String>()
            val job = launch {
                repositoryUT.getData(1).toList(resultList)
            }
            runCurrent()

            assertEquals("Result", resultList[0])

            job.cancel()
        }
    }
}
