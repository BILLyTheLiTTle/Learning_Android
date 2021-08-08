package learning.android.compose.ui.screens.tabbing

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState, scope: CoroutineScope, tabsItems: Array<String>, onClick: suspend (Int) -> Unit){
    Column {
        TabRow(selectedTabIndex = 0,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            }) {
            tabsItems.forEachIndexed{ index, tab ->
                Tab(selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            onClick(index)
                        }
                    },
                    text = {
                        Text(text = tab)
                    })
            }
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabsContent(pagerState: PagerState, onScroll: @Composable (Int) -> Unit) {
    HorizontalPager(state = pagerState) { page ->
        onScroll(page)
    }
}