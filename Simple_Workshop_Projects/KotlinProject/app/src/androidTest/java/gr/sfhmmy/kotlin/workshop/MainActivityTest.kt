package gr.sfhmmy.kotlin.workshop

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    var activityScenarioRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(
        MainActivity::class.java
    )

    @Test
    fun calculateGreetingForKid() {
        Espresso.onView(ViewMatchers.withId(R.id.name_editText)).perform(ViewActions.typeText("Bill"))
        Espresso.onView(ViewMatchers.withId(R.id.age_editText))
            .perform(ViewActions.typeText("4"), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.button)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.greeting_output_textView))
            .check(ViewAssertions.matches(ViewMatchers.withText("Hello Kid")))
    }
}