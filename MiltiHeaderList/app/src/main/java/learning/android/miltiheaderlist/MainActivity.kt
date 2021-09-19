package learning.android.miltiheaderlist

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import learning.android.miltiheaderlist.ui.theme.MiltiHeaderListTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MiltiHeaderListTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainList(getTheData())
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalFoundationApi
@Composable
fun MainList(data: List<Item>) {
    // Use state to achieve re-compose
    val listState = rememberLazyListState()
    val header = remember {
        mutableStateOf(data[0].type)
    }

    // Use state to understand if the user is moving to the start or the end of the list
    val firstItemIndex = remember {
        mutableStateOf(0)
    }

    // create a list which is a mirror of the LazyColumn
    val dataList = remember {
        mutableListOf<Item>()
    }
    val groupHeader = data.groupBy { it.type }
    groupHeader.forEach { (header, items) ->
        dataList.add(Item(type = header))
        val groupSubHeader = items.groupBy { it.subType }
        groupSubHeader.forEach { (subheader, items2) ->
            dataList.add(Item(type = header, subType = subheader))
            dataList.addAll(items2)
        }
    }

    val mainGroup = data.groupBy { it.type }
    Column() {
        // This header's content will be updated while scrolling
        Header(header = header.value)
        LazyColumn(state = listState) {
            mainGroup.forEach { (type, groupedData) ->
                if (header.value !== type) {
                    // This header visibility will be updated while scrolling
                    stickyHeader { Header(header = type) }
                }
                val subGroup = groupedData.groupBy { it.subType }
                subGroup.forEach { (subtype, subGroupedData)->
                    stickyHeader {
                        // Create only the subheader item
                        Header(subheader = "$subtype (${listState.firstVisibleItemIndex})")
                    }

                    items(items = subGroupedData) {
                        //listState.firstVisibleItemIndex.toString()
                        SimpleItem(item = it)

                        /* This is doing the magic for visibility and content of the above headers
                            depending on the user scrolling action
                         */
                        if (listState.firstVisibleItemIndex < firstItemIndex.value) { // move to start
                            if (listState.firstVisibleItemIndex > 0) {
                                header.value = dataList[listState.firstVisibleItemIndex - 1].type
                            }
                        }
                         else if (listState.firstVisibleItemIndex > firstItemIndex.value) { // move to the end
                            header.value = dataList[listState.firstVisibleItemIndex].type
                        }
                        firstItemIndex.value = listState.firstVisibleItemIndex
                    }
                }
            }
        }
    }
}

@Composable
fun SimpleItem(item : Item) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable { },
        elevation = 10.dp
    ) {
        Row() {
            Image(
                painter = rememberImagePainter(
                    data = item.imageUrl,
                    builder = {
                        transformations(CircleCropTransformation())
                    }
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .padding(5.dp)
            )
            Column() {
                Text(text = item.title, fontSize = 15.sp)
                Text(text = item.description, fontSize = 10.sp)
            }
        }
    }
}

@Composable
fun Header(header: String = "", subheader: String = "") {
    if (header.isNotEmpty()) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = 5.dp,
            backgroundColor = Color.Red
        ) {
            Text(text = header, fontSize = 20.sp, modifier = Modifier.padding(5.dp))
        }
    }
    if (subheader.isNotEmpty()) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = 5.dp,
            backgroundColor = Color.Red
        ) {
            Text(text = subheader, fontSize = 20.sp, modifier = Modifier.padding(5.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MiltiHeaderListTheme {
        //MainList()
    }
}

@Preview(showBackground = true)
@Composable
fun SimpleItemPreview() {
    MiltiHeaderListTheme {
        SimpleItem(item = Item("Movie", "Action",
            "Shang-Chi and the Legend of the Ten Rings",
            "Shang-Chi, the master of unarmed weaponry-based Kung Fu, is forced to " +
                    "confront his past after being drawn into the Ten Rings organization.",
            "https://threepixelslab.gr/wp-content/uploads/2021/04/Shang-Chi-and-the-Legend-of-the-Ten-Rings.jpg"))
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderPreview() {
    Header(header = "header")
}