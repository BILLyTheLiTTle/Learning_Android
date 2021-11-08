package learning.android.dogbreeds.ui.screens.breeds.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import learning.android.dogbreeds.ui.navigation.NavigationRoutes
import learning.android.dogbreeds.ui.theme.DogBreedsTheme
import learning.android.dogbreeds.ui.widgets.Error
import learning.android.dogbreeds.ui.widgets.LoadingListItem
import learning.android.dogbreeds.ui.widgets.Loading
import learning.android.dogbreeds.ui.widgets.list.viewstates.ErrorListItem

@Composable
fun BreedsList(navController: NavHostController) {
    val viewModel: BreedsListViewModel = hiltViewModel()
    val content = viewModel.breedsResult.collectAsLazyPagingItems()
//    LaunchedEffect(Unit) {
//        viewModel.getBreeds()
//    }

    /*
    It seems that swipe-to-refresh has an issue when you are doing it crazy fast.
    So, I had to invent a way to slow down the reaction to user actions in a deterministic way.
    It seems that the issue is fixed!
     */
    val isFetchingState = BreedSource.isFetchingState.collectAsState()
    SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing = false), onRefresh = {
        if (!isFetchingState.value) {
            content.refresh()
        }
    }) {
        LazyColumn(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            items(content) {
                BreedCard(
                    name = it?.name ?: "",
                    height = it?.height?.metric ?: "",
                    weight = it?.weight?.metric ?: "",
                    description = it?.description ?: "",
                    imgUrl = it?.image?.url ?: "",
                    onClick = {
                        navController.navigate("${NavigationRoutes.BREED_DETAILS}${it?.id ?: ""}")
                    }
                )
            }

            item {
                content.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            Loading(modifier = Modifier.fillParentMaxSize())
                        }
                        loadState.append is LoadState.Loading -> {
                            LoadingListItem(modifier = Modifier.fillMaxWidth())
                        }
                        loadState.refresh is LoadState.Error -> {
                            val e = loadState.refresh as LoadState.Error
                            Error(
                                modifier = Modifier.fillMaxWidth(),
                                msg = e.error.localizedMessage ?: "",
                                onClick = { retry() }
                            )
                        }
                        loadState.append is LoadState.Error -> {
                            val e = loadState.append as LoadState.Error
                            ErrorListItem(
                                modifier = Modifier.fillMaxWidth(),
                                msg = e.error.localizedMessage ?: "",
                                onClick = { retry() }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BreedsListPreview() {
    DogBreedsTheme {
        BreedsList(navController = NavHostController(LocalContext.current))
    }
}