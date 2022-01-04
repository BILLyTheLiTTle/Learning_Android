package learning.android.data.common

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class MockedServerFunctionality {

    val dispatcher: Dispatcher = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            when (request.path) {
                "/breeds?limit=1&page=0"
                -> return MockResponse().setResponseCode(200).setBody(loadJson("sample_breeds_list.json"))
                "/breeds/1"
                -> return MockResponse().setResponseCode(200).setBody(loadJson("sample_breed_details.json"))
                    .setBody("good")
            }
            return MockResponse().setResponseCode(404)
        }
    }

    fun loadJson(fileName: String) = javaClass.classLoader?.getResourceAsStream(fileName)?.bufferedReader()
            .use {
                    bufferReader -> bufferReader?.readText()
            } ?: ""
}