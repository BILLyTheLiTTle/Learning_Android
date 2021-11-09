package learning.android.dogbreeds.ui.screens.breeds.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import learning.android.dogbreeds.ui.theme.CareysPink
import learning.android.dogbreeds.ui.theme.Pink
import learning.android.dogbreeds.ui.theme.header
import learning.android.dogbreeds.ui.widgets.Error
import learning.android.dogbreeds.ui.widgets.Loading
import learning.android.dogbreeds.ui.widgets.image.CircularImage
import learning.android.domain.models.state.NetworkResult


private const val IMAGE_REFERENCE_ID = "image"
private const val SPECS_REFERENCE_ID = "specs"
private const val NAVIGATION_REFERENCE_ID = "navigation"
private const val LOADING_REFERENCE_ID = "loading"
private const val ERROR_REFERENCE_ID = "error"

@Composable
fun BreedDetails(breedId: String) {
    val viewModel: BreedDetailsViewModel = hiltViewModel()
    val content = viewModel.breedDetailsResult.collectAsState()
    val constraintSet = ConstraintSet {
        val image = createRefFor(IMAGE_REFERENCE_ID)
        val specs = createRefFor(SPECS_REFERENCE_ID)
        val navigation = createRefFor(NAVIGATION_REFERENCE_ID)
        val loading = createRefFor(LOADING_REFERENCE_ID)
        val error = createRefFor(ERROR_REFERENCE_ID)

        constrain(image) {
            top.linkTo(parent.top, 10.dp)
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

    LaunchedEffect(Unit) {
        viewModel.getBreedDetails(breedId)
    }

    // No need to use constraint layout but I would like to experiment
    ConstraintLayout(
        constraintSet, modifier = Modifier
            .fillMaxSize()
            .background(color = Pink)
    ) {
        when (content.value.status) {
            NetworkResult.Status.LOADING -> {
                Loading(
                    modifier = Modifier
                        .layoutId(LOADING_REFERENCE_ID)
                        .background(Color.Transparent)
                )
            }
            NetworkResult.Status.ERROR -> {
                Error(
                    msg = content.value.message.toString(), modifier = Modifier
                        .layoutId(
                            ERROR_REFERENCE_ID
                        )
                        .background(
                            Color.Transparent
                        )
                ) {

                }
            }
            else -> {
                BreedImage(imgUrl = content.value.data?.image?.url ?: "")

                BreedSpecs(
                    name = content.value.data?.name ?: "",
                    temperament = content.value.data?.temperament ?: "",
                    lifeSpan = content.value.data?.lifeSpan ?: "",
                    origin = content.value.data?.origin ?: "",
                    weight = content.value.data?.weight?.metric ?: "",
                    height = content.value.data?.height?.metric ?: "",
                    description = content.value.data?.description ?: ""
                )

                BreedNavigation(id = content.value.data?.id ?: 0)
            }
        }
    }
}

@Composable
private fun BreedImage(imgUrl: String) {
    Row(modifier = Modifier.layoutId(IMAGE_REFERENCE_ID)) {
        if (imgUrl.isNotEmpty()) {
            CircularImage(
                imgUrl = imgUrl,
                modifier = Modifier
                    .size(150.dp)
            )
        }
    }
}

@Composable
private fun BreedSpecs(
    name: String, temperament: String,
    lifeSpan: String, origin: String,
    weight: String, height: String,
    description: String
) {
    Column(
        modifier = Modifier
            .layoutId(SPECS_REFERENCE_ID)
            .verticalScroll(rememberScrollState())
    ) {
        if (name.isNotEmpty()) {
            Text(
                text = "Name: $name",
                style = header
            )
        }

        if (temperament.isNotEmpty()) {
            Text(
                text = "Temperament: $temperament",
                style = header
            )
        }

        if (lifeSpan.isNotEmpty()) {
            Text(
                text = "Lifespan: $lifeSpan",
                style = header
            )
        }

        if (origin.isNotEmpty()) {
            Text(
                text = "Origin: $origin",
                style = header
            )
        }

        if (weight.isNotEmpty()) {
            Text(
                text = "Weight: $weight (kg)",
                style = header
            )
        }

        if (height.isNotEmpty()) {
            Text(
                text = "Height: $height (cm)",
                style = header
            )
        }

        if (description.isNotEmpty()) {
            Text(
                text = "Description: $description",
                style = header
            )
        }
    }
}

@Composable
private fun BreedNavigation(id: Int) {
    Row(
        modifier = Modifier
            .layoutId(NAVIGATION_REFERENCE_ID)
            .fillMaxWidth(0.95f)
    ) {
        if (id > BreedDetailsViewModel.FIRST_ITEM_ID) {
            Button(
                modifier = Modifier.weight(10f),
                colors = ButtonDefaults.buttonColors(backgroundColor = CareysPink),
                onClick = {}) {
                Text(text = "Previous")
            }
        }

        if (id in (BreedDetailsViewModel.FIRST_ITEM_ID + 1) until BreedDetailsViewModel.LAST_ITEM_ID) {
            Spacer(modifier = Modifier.weight(1f))
        }

        if (id < BreedDetailsViewModel.LAST_ITEM_ID) {
            Button(modifier = Modifier
                .weight(10f),
                colors = ButtonDefaults.buttonColors(backgroundColor = CareysPink),
                onClick = {}) {
                Text(text = "Next")
            }
        }
    }
}