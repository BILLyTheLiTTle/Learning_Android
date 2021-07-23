package gr.sfhmmy.java.workshop;

import org.junit.Assert;
import org.junit.Test;

public class MainActivityViewModelTest {

    @Test
    public void testCalculateGreetingForKid() {
        MainActivityViewModel viewModel = new MainActivityViewModel();
        String actual = viewModel.calculatingGreeting(4, "Bill");
        String expected = "Hello Kid";
        Assert.assertEquals(expected, actual);
    }

}