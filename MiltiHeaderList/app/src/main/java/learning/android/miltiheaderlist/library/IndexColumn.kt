package learning.android.miltiheaderlist.library

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalFoundationApi
@Composable
fun <T> IndexColumn(
    indices: List<T>,
    itemsListState: LazyListState,
    modifier: Modifier,
    predicate: (T) -> Int,
    lazyColumnContent: @Composable () -> Unit,
    indexedItemContent: @Composable (T) -> Unit
) {
    val coroutineContext = rememberCoroutineScope()

    Box(contentAlignment = Alignment.CenterEnd) {
        Column() {
            lazyColumnContent()
        }
        Column(
            horizontalAlignment = Alignment.End,
            modifier = modifier
                .verticalScroll(rememberScrollState())
        ) {
            indices.forEach { item ->
                Box(modifier = Modifier
                    .padding(5.dp)
                    .clickable {
                        coroutineContext.launch {
                            val itemIndex = predicate(item)
                            if (itemIndex >= 0) {
                                itemsListState.scrollToItem(itemIndex)
                            }
                        }
                    }) {
                    indexedItemContent(item)
                }
            }
        }
    }
}