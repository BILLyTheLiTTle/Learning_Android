package learning.android.compose.ui.screens.main.weather

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import learning.android.compose.ui.list.WeatherItem
import learning.android.compose.ui.screens.main.states.*
import learning.android.compose.ui.theme.JetpackComposeExampleTheme

@Composable
fun WeatherItemsScreen(viewModel: WeatherItemsViewModel) {
    val state = remember { viewModel.uiState }
    val items = remember { viewModel.fetchData() }
    val selectedIndex = remember { viewModel.selectedIndex }

    Box(Modifier.fillMaxSize()) {
        when (state.value) {
            is LoadedState -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier
                        .fillMaxHeight(1f)
                        .padding(5.dp)
                ) {
                    itemsIndexed(items) { index, item ->
                        WeatherItem(weatherInfo = item,
                            selected = selectedIndex.value == index,
                        onClick = { selectedIndex.value = index })
                    }
                }
            }
            is InitialState -> {
                Text(text = "Prepare to load data", modifier = Modifier.align(alignment = Alignment.Center))
            }
            is LoadingState -> {
                selectedIndex.value = -1
                CircularProgressIndicator(modifier = Modifier.align(alignment = Alignment.Center))
            }
        }
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DummyWeatherItems() {
    JetpackComposeExampleTheme {
        WeatherItemsScreen(WeatherItemsViewModel())
    }
}