package learning.android.kmm

import org.junit.Assert.assertEquals
import org.junit.Test

class AndroidGreetingTest {

    // A simple platform specific test with constructor DI
    @Test
    fun testExample() {
        val name = "Bill"
        val result = Greeting(AndroidPlatform()).greeting(name)

        assertEquals("Hello $name, you are in Android 0!", result )
    }
}