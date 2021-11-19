package learning.android.dogbreeds.ui.screens.breeds.list

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import learning.android.dogbreeds.MainActivity
import learning.android.dogbreeds.R
import learning.android.dogbreeds.ui.theme.DogBreedsTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class BreedsListTest {

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

    @Test
    fun isBreedsListVisible() {
        composeTestRule.setContent {
            DogBreedsTheme {
                BreedsList{ }
            }
        }

        composeTestRule.onNodeWithContentDescription(activity.getString(R.string.breeds_list)).assertIsDisplayed()
    }

    @Test
    fun clickSpecificItem_makeSureExists() {
        val dogBreedForTesting = "Cerberus"

        composeTestRule.setContent {
            DogBreedsTheme {
                BreedsList{ }
            }
        }
        composeTestRule.waitUntil(timeoutMillis = 10_000) {
            composeTestRule
                .onAllNodesWithContentDescription(dogBreedForTesting)
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule.onNodeWithContentDescription(dogBreedForTesting).performClick()
    }
}