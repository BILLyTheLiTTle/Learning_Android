package learning.android.miltiheaderlist.library

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalFoundationApi
@Composable
fun <T> IndexColumnWithCustomLazyColumn(
    indices: List<T>,
    itemsListState: LazyListState,
    modifier: Modifier = Modifier,
    predicate: (T) -> Int,
    lazyColumnContent: @Composable () -> Unit,
    indexedItemContent: @Composable (T, Boolean) -> Unit
) {
    val coroutineContext = rememberCoroutineScope()
    val indexState = rememberLazyListState()
    val shouldUpdateSelection = remember {
        mutableStateOf(false)
    }
    val selectedIndex = remember {
        mutableStateOf(0)
    }

    Box(contentAlignment = Alignment.CenterEnd) {
        Column() {
            lazyColumnContent()
        }

        LazyColumn(
            state = indexState,
            horizontalAlignment = Alignment.End,
            modifier = modifier
        ) {
            if (!indexState.isScrollInProgress && shouldUpdateSelection.value) {
                val list = indexState.layoutInfo.visibleItemsInfo
                val startIndex = list.first().index

                scrollListBasedOnIndex(
                    coroutineContext, predicate,
                    indices, itemsListState,
                    selectedIndex, startIndex
                )

                shouldUpdateSelection.value = false
            } else if (indexState.isScrollInProgress) {
                shouldUpdateSelection.value = true
            }

            itemsIndexed(indices) { index, item ->
                Box(modifier = Modifier
                    .padding(5.dp)
                    .clickable {
                        scrollListBasedOnIndex(
                            coroutineContext, predicate,
                            indices, itemsListState,
                            selectedIndex, index
                        )
                    }) {
                    indexedItemContent(item, index == selectedIndex.value)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalFoundationApi
@Composable
fun <T> IndexColumnWithData(
    indices: List<T>,
    data: List<T>,
    modifier: Modifier = Modifier,
    predicate: (T) -> Int,
    mainItemContent: @Composable (Int) -> Unit,
    indexedItemContent: @Composable (T, Boolean) -> Unit
) {
    val coroutineContext = rememberCoroutineScope()
    val indexState = rememberLazyListState()
    val itemsState = rememberLazyListState()
    val shouldUpdateSelection = remember {
        mutableStateOf(false)
    }
    val selectedIndex = remember {
        mutableStateOf(0)
    }

    Box(contentAlignment = Alignment.CenterEnd) {
        Column() {
            LazyColumn(state = itemsState) {
                itemsIndexed(data) { index, item ->
                    mainItemContent(index)
                }
            }
        }

        LazyColumn(
            state = indexState,
            horizontalAlignment = Alignment.End,
            modifier = modifier
        ) {
            if (!indexState.isScrollInProgress && shouldUpdateSelection.value) {
                val list = indexState.layoutInfo.visibleItemsInfo
                val startIndex = list.first().index

                scrollListBasedOnIndex(
                    coroutineContext, predicate,
                    indices, itemsState,
                    selectedIndex, startIndex
                )

                shouldUpdateSelection.value = false
            } else if (indexState.isScrollInProgress) {
                shouldUpdateSelection.value = true
            }

            itemsIndexed(indices) { index, item ->
                Box(modifier = Modifier
                    .padding(5.dp)
                    .clickable {
                        scrollListBasedOnIndex(
                            coroutineContext, predicate,
                            indices, itemsState,
                            selectedIndex, index
                        )
                    }) {
                    indexedItemContent(item, index == selectedIndex.value)
                }
            }
        }
    }
}

private fun <T> scrollListBasedOnIndex(
    coroutineContext: CoroutineScope,
    predicate: (T) -> Int,
    indices: List<T>,
    itemsListState: LazyListState,
    selectedIndex: MutableState<Int>,
    index: Int
) {
    coroutineContext.launch {
        val itemIndex = predicate(indices[index])
        if (itemIndex >= 0) {
            itemsListState.scrollToItem(itemIndex)
        }
        selectedIndex.value = index
    }
}