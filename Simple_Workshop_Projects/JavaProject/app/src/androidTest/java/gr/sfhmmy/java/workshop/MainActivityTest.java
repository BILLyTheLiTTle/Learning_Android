package gr.sfhmmy.java.workshop;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;

import org.junit.Rule;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void calculateGreetingForKid() {
        onView(withId(R.id.name_editText)).perform(typeText("Bill"));
        onView(withId(R.id.age_editText)).perform(typeText("4"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.greeting_output_textView)).check(matches(withText("Hello Kid")));
    }
}