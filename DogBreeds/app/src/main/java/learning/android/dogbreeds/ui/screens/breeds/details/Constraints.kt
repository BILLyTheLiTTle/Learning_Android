package learning.android.dogbreeds.ui.screens.breeds.details

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension

const val IMAGE_REFERENCE_ID = "image"
const val SPECS_REFERENCE_ID = "specs"
const val NAVIGATION_REFERENCE_ID = "navigation"
const val LOADING_REFERENCE_ID = "loading"
const val ERROR_REFERENCE_ID = "error"

fun getConstraints(imageConstraintPoint: Dp): ConstraintSet {
    return ConstraintSet {
        val image = createRefFor(IMAGE_REFERENCE_ID)
        val specs = createRefFor(SPECS_REFERENCE_ID)
        val navigation = createRefFor(NAVIGATION_REFERENCE_ID)
        val loading = createRefFor(LOADING_REFERENCE_ID)
        val error = createRefFor(ERROR_REFERENCE_ID)

        constrain(image) {
            top.linkTo(parent.top, imageConstraintPoint)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(specs) {
            top.linkTo(image.bottom, 5.dp)
            bottom.linkTo(navigation.top, 5.dp)
            start.linkTo(parent.start, 5.dp)
            end.linkTo(parent.end, 5.dp)
            height = Dimension.fillToConstraints
        }

        constrain(navigation) {
            bottom.linkTo(parent.bottom, 5.dp)
            start.linkTo(parent.start, 5.dp)
            end.linkTo(parent.end, 5.dp)
        }

        constrain(loading) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(error) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

    }
}