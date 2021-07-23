package gr.sfhmmy.kotlin.workshop

import org.junit.Assert
import org.junit.Test

class MainActivityViewModelTest {
    @Test
    fun testCalculateGreetingForKid() {
        val viewModel = MainActivityViewModel()
        val actual = viewModel.calculatingGreeting(4, "Bill")
        val expected = "Hello Kid"
        Assert.assertEquals(expected, actual)
    }
}