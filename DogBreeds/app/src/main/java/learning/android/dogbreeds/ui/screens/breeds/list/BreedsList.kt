package learning.android.dogbreeds.ui.screens.breeds.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import learning.android.dogbreeds.ui.theme.DogBreedsTheme

@Composable
fun BreedsList() {
    val viewModel: BreedsListViewModel = viewModel()
    val content = viewModel.breedsResult.collectAsState()
//    LaunchedEffect(Unit) {
//        viewModel.getBreeds()
//    }

    LazyColumn(modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(8.dp)) {

        items(content.value.data ?: emptyList()) {
            BreedCard(name = it.name ?:"",
                height = it.height.metric,
                weight = it.weight.metric,
                description = it.description ?: "",
                imgUrl = it.image.url)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BreedsListPreview() {
    DogBreedsTheme {
        BreedsList()
    }
}