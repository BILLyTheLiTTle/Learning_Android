package learning.android.data.repo

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import learning.android.data.api.network.ApiService
import learning.android.data.common.MockedServerFunctionality
import learning.android.data.mappers.BreedMapper
import learning.android.domain.models.state.Status
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteRepoImplTest {

    private val mockedServer = MockWebServer()
    private val mockedServerFunctionality = MockedServerFunctionality()
    private val apiService = Retrofit.Builder()
        .baseUrl("http://127.0.0.1:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(ApiService::class.java)
    private val breedMapper = BreedMapper()

    @Before
    fun setUp() {
        mockedServer.start(8080)
    }

    @After
    fun tearDown() {
        mockedServer.shutdown()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_with200ResponseCodeUsingMockServer_whenGetBreeds_shouldHaveSuccessStatus() {
        // runBlockingTest not working properly (https://stackoverflow.com/a/68249932/1392366)
        runBlocking {
            val mockedResponse = mockedServerFunctionality.loadJson("sample_breeds_list.json")
            mockedServer.enqueue(MockResponse().setResponseCode(200).setBody(mockedResponse))

            val result = RemoteRepoImpl(apiService, breedMapper).getBreeds(1,0)
            val request = mockedServer.takeRequest()

            assertEquals("/breeds?limit=1&page=0", request.path)
            assertEquals(Status.SUCCESS, result.status)
            assertEquals("Affenpinscher", result.data!![0].name) // It is my duty to have a non null value for test purposes
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_with200ResponseCodeUsingMockServerCustomDispatcher_whenGetBreeds_shouldHaveSuccessStatus() {
        // runBlockingTest not working properly (https://stackoverflow.com/a/68249932/1392366)
        runBlocking {
            mockedServer.dispatcher = mockedServerFunctionality.dispatcher

            val result = RemoteRepoImpl(apiService, breedMapper).getBreeds(1,0)
            val request = mockedServer.takeRequest()

            assertEquals("/breeds?limit=1&page=0", request.path)
            assertEquals(Status.SUCCESS, result.status)
            assertEquals("Affenpinscher", result.data!![0].name) // It is my duty to have a non null value for test purposes
        }
    }

    @Test
    fun test_with300ResponseCodeUsingMockServer_whenGetBreeds_shouldFail() {
        // runBlockingTest not working properly (https://stackoverflow.com/a/68249932/1392366)
        runBlocking {
            mockedServer.enqueue(MockResponse().setResponseCode(300).setBody(""))

            val result = RemoteRepoImpl(apiService, breedMapper).getBreeds(10, 1)

            assertEquals(Status.ERROR, result.status)
            assert(result.error is HttpException)
            assertEquals("HTTP 300 Redirection", result.message)
            assertEquals(null, result.data)
        }
    }
}