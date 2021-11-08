package learning.android.dogbreeds.ui.screens.breeds.details

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import learning.android.dogbreeds.ui.theme.header

@Composable
fun BreedDetails(breedId: String) {
    Text(
        text = breedId,
        style = header
    )
}