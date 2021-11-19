package learning.android.dogbreeds.ui.screens.breeds.details

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import learning.android.dogbreeds.MainActivity
import learning.android.dogbreeds.R
import learning.android.dogbreeds.ui.theme.DogBreedsTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class BreedDetailsTest {

    @get: Rule(order = 0)
    val hiltTestRule = HiltAndroidRule(this)

    @get: Rule(order = 1)
    // By using createAndroidComposeRule I achieved to get the viewmodel!
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val activity by lazy { composeTestRule.activity }

    @Before
    fun setup() {
        hiltTestRule.inject()

    }

    @ExperimentalAnimationApi
    @Test
    fun openFirstItemBreedDetails_checkVisibleItems() {
        val dogBreedForTesting = "Cerberus"

        composeTestRule.setContent {
            DogBreedsTheme {
                BreedDetails(breedId = "0")
            }
        }
        composeTestRule.waitUntil(timeoutMillis = 10_000) {
            composeTestRule
                .onAllNodesWithContentDescription(dogBreedForTesting)
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule.onNodeWithContentDescription(dogBreedForTesting).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(activity.getString(R.string.breed_specs)).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(activity.getString(R.string.previous_breed)).assertDoesNotExist()
        composeTestRule.onNodeWithContentDescription(activity.getString(R.string.next_breed)).assertIsDisplayed()
    }

    @ExperimentalAnimationApi
    @Test
    fun openSecondItemBreedDetails_checkVisibleItems() { // replicates any middle item (first...last)
        val dogBreedForTesting = "Affenpinscher"

        composeTestRule.setContent {
            DogBreedsTheme {
                BreedDetails(breedId = "1")
            }
        }
        composeTestRule.waitUntil(timeoutMillis = 10_000) {
            composeTestRule
                .onAllNodesWithContentDescription(dogBreedForTesting)
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule.onNodeWithContentDescription(dogBreedForTesting).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(activity.getString(R.string.breed_specs)).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(activity.getString(R.string.previous_breed)).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(activity.getString(R.string.next_breed)).assertIsDisplayed()
    }
}