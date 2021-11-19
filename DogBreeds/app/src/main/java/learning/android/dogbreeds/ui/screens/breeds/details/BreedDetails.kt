package learning.android.dogbreeds.ui.screens.breeds.details

import androidx.compose.animation.*
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import learning.android.dogbreeds.R
import learning.android.dogbreeds.ui.theme.CareysPink
import learning.android.dogbreeds.ui.theme.Pink
import learning.android.dogbreeds.ui.theme.header
import learning.android.dogbreeds.ui.widgets.Error
import learning.android.dogbreeds.ui.widgets.Loading
import learning.android.dogbreeds.ui.widgets.image.CircularImage
import learning.android.domain.models.state.Status

@ExperimentalAnimationApi
@Composable
fun BreedDetails(breedId: String) { // In my LaunchedEffect() implementation this breedId value never changes
    val viewModel: BreedDetailsViewModel = hiltViewModel()
    val content = viewModel.breedDetailsResult.collectAsState()
    val breedSpecsStr = stringResource(id = R.string.breed_specs)

    // Transition Animation values
    var fullScreenImageState by remember { mutableStateOf(false) }
    val transition = updateTransition(fullScreenImageState, label = "fullScreenImageState")
    val breedImageTopGap = transition.animateDp(label = "breedImageTopGap") {
        if (it) 150.dp else 10.dp
    }
    val breedImageSize = transition.animateDp(label = "breedImageTopGap") {
        if (it) 300.dp else 150.dp
    }

    val constraintSet = getConstraints(breedImageTopGap.value)

    val swipeableId = remember {
        mutableStateOf(100L)
    }

    // LaunchedEffect() implementation
//    val state = remember {
//        mutableStateOf(breedId)
//    }
//    LaunchedEffect(state) {
    LaunchedEffect(Unit) {
        viewModel.getBreedDetails(breedId)
//        viewModel.getBreedDetails(state.value) // LaunchedEffect() implementation
    }

    // No need to use constraint layout but I would like to experiment
    ConstraintLayout(
        constraintSet, modifier = Modifier
            .fillMaxSize()
            .background(color = Pink)
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    if (change.id.value != swipeableId.value) {
                        swipeableId.value = change.id.value
                        if (dragAmount < 0f) {
                            if (content.value.data?.id ?: BreedDetailsViewModel.LAST_ITEM_ID < BreedDetailsViewModel.LAST_ITEM_ID) {
                                viewModel.setUserAction(
                                    NextBreed(
                                        (content.value.data?.id ?: 1) + 1
                                    )
                                )
                            }
                        } else if (dragAmount > 0f) {
                            if (content.value.data?.id ?: BreedDetailsViewModel.FIRST_ITEM_ID > BreedDetailsViewModel.FIRST_ITEM_ID) {
                                viewModel.setUserAction(
                                    PreviousBreed(
                                        (content.value.data?.id ?: 1) - 1
                                    )
                                )
                            }
                        }
                    }
                }
            }
    ) {
        when (content.value.status) {
            Status.LOADING -> {
                Loading(
                    modifier = Modifier
                        .layoutId(LOADING_REFERENCE_ID)
                        .background(Color.Transparent)
                )
            }
            Status.ERROR -> {
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
                val specsExpandedState = remember {
                    mutableStateOf(true)
                }

                BreedImage(imgUrl = content.value.data?.image?.url ?: "",
                    size = breedImageSize.value,
                    modifier = Modifier.semantics { contentDescription = content.value.data?.name ?: "" },
                    onClick = {
                        specsExpandedState.value = !specsExpandedState.value
                        fullScreenImageState = !fullScreenImageState
                    }
                )

                BreedSpecs(
                    modifier = Modifier.semantics { contentDescription = breedSpecsStr },
                    name = content.value.data?.name ?: "",
                    temperament = content.value.data?.temperament ?: "",
                    lifeSpan = content.value.data?.lifeSpan ?: "",
                    origin = content.value.data?.origin ?: "",
                    weight = content.value.data?.weight?.metric ?: "",
                    height = content.value.data?.height?.metric ?: "",
                    description = content.value.data?.description ?: "",
                    isExpanded = specsExpandedState.value
                )

                BreedNavigation(
                    id = content.value.data?.id ?: 0,
                    viewModel
                )//, state) // LaunchedEffect() implementation
            }
        }
    }
}

@Composable
private fun BreedImage(imgUrl: String, modifier: Modifier = Modifier, size: Dp, onClick: () -> Unit) {
    Row(
        modifier = modifier
            .layoutId(IMAGE_REFERENCE_ID)
    ) {
        if (imgUrl.isNotEmpty()) {
            CircularImage(
                imgUrl = imgUrl,
                modifier = Modifier
                    .size(size)
                    .clickable(onClick = onClick)
            )
        }
    }
}

@ExperimentalAnimationApi
@Composable
private fun BreedSpecs(
    modifier: Modifier = Modifier,
    name: String, temperament: String,
    lifeSpan: String, origin: String,
    weight: String, height: String,
    description: String,
    isExpanded: Boolean
) {
    AnimatedVisibility(
        visible = isExpanded,
        enter = fadeIn(initialAlpha = 0.1f),
        exit = fadeOut(),
        modifier = modifier.layoutId(SPECS_REFERENCE_ID)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.97f)
                .verticalScroll(rememberScrollState())
        ) {
            if (name.isNotEmpty()) {
                val isNameExpanded = remember { mutableStateOf(false) }
                BreedSpecsLine(title = stringResource(id = R.string.breed_specs_name_label),
                    value = if (!isNameExpanded.value) name.take(5).plus("...") else name,
                    modifier = Modifier
                        .animateEnterExit(enter = slideInVertically(), exit = slideOutVertically())
                        .animateContentSize(
                            animationSpec = tween(
                                durationMillis = 1000,
                                easing = LinearOutSlowInEasing
                            )
                        )
                        .clickable { isNameExpanded.value = !isNameExpanded.value }
                )
            }

            if (temperament.isNotEmpty()) {
                val isTemperamentExpanded = remember { mutableStateOf(false) }
                BreedSpecsLine(title = stringResource(id = R.string.breed_specs_temperament_label),
                    value = if (!isTemperamentExpanded.value) temperament.take(8).plus("...") else temperament,
                    modifier = Modifier
                        .animateEnterExit(enter = expandIn(), exit = shrinkOut())
                        .animateContentSize()
                        .clickable { isTemperamentExpanded.value = !isTemperamentExpanded.value }
                )
            }

            if (lifeSpan.isNotEmpty()) {
                BreedSpecsLine(title = stringResource(id = R.string.breed_specs_lifespan_label), value = lifeSpan,
                    modifier = Modifier.animateEnterExit())
            }

            if (origin.isNotEmpty()) {
                BreedSpecsLine(title = stringResource(id = R.string.breed_specs_origin_label), value = origin)
            }

            if (weight.isNotEmpty()) {
                BreedSpecsLine(title = stringResource(id = R.string.breed_specs_weight_label), value = "$weight (kg)",
                    modifier = Modifier.animateEnterExit())
            }

            if (height.isNotEmpty()) {
                BreedSpecsLine(title = stringResource(id = R.string.breed_specs_height_label), value = "$height (cm)",
                    modifier = Modifier.animateEnterExit())
            }

            if (description.isNotEmpty()) {
                val isDescExpanded = remember { mutableStateOf(false) }
                BreedSpecsLine(title = stringResource(id = R.string.breed_specs_description_label),
                    value = if (!isDescExpanded.value) description.take(20).plus("...") else description,
                    modifier = Modifier
                        .animateContentSize(
                            animationSpec = tween(
                                durationMillis = 1000,
                                easing = LinearOutSlowInEasing
                            )
                        )
                        .clickable { isDescExpanded.value = !isDescExpanded.value }
                )
            }
        }
    }
}

@Composable
private fun BreedSpecsLine(title: String, value: String,
modifier: Modifier = Modifier) {
    Text(
        text = "$title: $value",
        style = header,
        modifier = modifier
    )
    Spacer(modifier = Modifier.height(5.dp))
}

@Composable
private fun BreedNavigation(
    id: Int,
    viewModel: BreedDetailsViewModel
) { //, state: MutableState<String>) {// LaunchedEffect() implementation
    val previousBreedStr = stringResource(id = R.string.previous_breed)
    val nextBreedStr = stringResource(id = R.string.next_breed)

    Row(
        modifier = Modifier
            .layoutId(NAVIGATION_REFERENCE_ID)
            .fillMaxWidth(0.95f)
    ) {
        if (id > BreedDetailsViewModel.FIRST_ITEM_ID) {
            Button(
                modifier = Modifier
                    .weight(10f)
                    .semantics { contentDescription = previousBreedStr },
                colors = ButtonDefaults.buttonColors(backgroundColor = CareysPink),
                onClick = {
//                    viewModel.getBreedDetails((id-1).toString()) // just call the function
                    viewModel.setUserAction(PreviousBreed(id - 1))
//                    state.value = "${state.value.toInt() - 1}"// LaunchedEffect() implementation
                }) {
                Text(text = previousBreedStr)
            }
        }

        if (id in (BreedDetailsViewModel.FIRST_ITEM_ID + 1) until BreedDetailsViewModel.LAST_ITEM_ID) {
            Spacer(modifier = Modifier.weight(1f))
        }

        if (id < BreedDetailsViewModel.LAST_ITEM_ID) {
            Button(modifier = Modifier
                .weight(10f)
                .semantics { contentDescription = nextBreedStr },
                colors = ButtonDefaults.buttonColors(backgroundColor = CareysPink),
                onClick = {
//                    viewModel.getBreedDetails((id+1).toString()) // just call the function
                    viewModel.setUserAction(NextBreed(id + 1))
//                    state.value = "${state.value.toInt() + 1}"// LaunchedEffect() implementation
                }) {
                Text(text = nextBreedStr)
            }
        }
    }
}