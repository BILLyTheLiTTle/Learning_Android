package learning.android.dogbreeds.ui.screens.breeds.widgets.image

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import learning.android.dogbreeds.ui.theme.DogBreedsTheme
import learning.android.dogbreeds.ui.widgets.image.CircularImage
import org.junit.Rule
import org.junit.Test

class CircularImageTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun image_invalidUrl_showPlaceholder() {
        composeTestRule.setContent {
            DogBreedsTheme {
                CircularImage(imgUrl = "", modifier = Modifier)
            }
        }

        // TODO How to check if the error image is shown?
//        InstrumentationRegistry.getInstrumentation()
//            .targetContext.resources.getString(R.string.load_error)
    }
}