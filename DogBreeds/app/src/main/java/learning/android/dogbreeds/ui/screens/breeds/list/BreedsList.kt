package learning.android.dogbreeds.ui.screens.breeds.list

import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import learning.android.dogbreeds.ui.theme.DogBreedsTheme

@Composable
fun BreedsList() {
    val viewModel: BreedsListViewModel = viewModel()
//    LaunchedEffect(Unit) {
//        viewModel.getBreeds()
//    }
    Text(text = viewModel.breedsResult.collectAsState().value.data.toString())
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DogBreedsTheme {
        BreedsList()
    }
}