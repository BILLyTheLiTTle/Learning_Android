package learning.android.dogbreeds.ui.screens.breeds.details

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import learning.android.dogbreeds.ui.theme.header

@Composable
fun BreedDetails(breedId: String) {
    val viewModel: BreedDetailsViewModel = hiltViewModel()
    val content = viewModel.breedDetailsResult.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getBreedDetails(breedId)
    }
    Text(
        text = content.value.data.toString(),
        style = header
    )
}