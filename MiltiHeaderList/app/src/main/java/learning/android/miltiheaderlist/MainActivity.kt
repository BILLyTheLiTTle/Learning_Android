package learning.android.miltiheaderlist

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import learning.android.miltiheaderlist.library.*
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
                    //MainList(getTheData())
//                    ExampleDoubleHeaderList(getTheData())
                    ExampleIndexedListWithCustomLazyColumn(getTheIndexedData())
//                    ExampleIndexedListWithData(getTheIndexedData())
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun ExampleIndexedListWithCustomLazyColumn(data: List<CustomListItem2>,
                       indices: List<Char> = ('A'..'Z').toList()) {
    val lazyListState = rememberLazyListState()

    IndexColumnWithCustomLazyColumn(
        // The list of the indices
        indices = indices,
        // The state of the main LazyColumn, the one with the real data
        itemsListState = lazyListState,
        // The modifier is exported for the Column, the one with the indices
        modifier = Modifier
            .background(color = Color.Transparent)
            .height(300.dp),
        // The way to connect the index with a data item (here the index item matches the first letter of the surname)
        predicate = {
            data.indexOfFirst { item ->
                item.surname.startsWith(it.toString(), true) }
                    },
        // The list of the main data
        lazyColumnContent = {
            LazyColumn(state = lazyListState) {
                items(data) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                            .clickable { },
                        elevation = 10.dp
                    ) {
                        Column {
                            Text(text = it.surname,
                                fontSize = 17.sp, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic,
                                modifier = Modifier.padding(start = 10.dp))
                            Text(text = it.name,
                                fontSize = 15.sp, fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(start = 15.dp))
                        }
                    }
                }
            }
        },
        // The item content for the indices list
        indexedItemContent = { item, isSelected ->
            Text(
                modifier = Modifier
                    .background(color = Color.Transparent),
                color = if (isSelected) Color.Blue else Color.Black,
                text = item.toString(),
                fontSize = 20.sp)
        }
    )
}

@ExperimentalFoundationApi
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun ExampleIndexedListWithData(data: List<CustomListItem2>,
                       indices: List<Char> = ('A'..'Z').toList()) {

    IndexColumnWithData(
        // The list of the indices
        indices = indices,
        // The list of the actual data
        data = data,
        // The modifier is exported for the Column, the one with the indices
        modifier = Modifier
            .background(color = Color.Transparent)
            .height(300.dp),
        // The way to connect the index with a data item (here the index item matches the first letter of the surname)
        predicate = {
            data.indexOfFirst { item ->
                item.surname.startsWith(it.toString(), true) }
        },
        // The list of the main data
        mainItemContent = {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .clickable { },
                elevation = 10.dp
            ) {
                Column {
                    Text(text = data[it].surname,
                        fontSize = 17.sp, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic,
                        modifier = Modifier.padding(start = 10.dp))
                    Text(text = data[it].name,
                        fontSize = 15.sp, fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(start = 15.dp))
                }
            }
        },
        // The item content for the indices list
        indexedItemContent = { item, isSelected ->
            Text(
                modifier = Modifier
                    .background(color = Color.Transparent),
                color = if (isSelected) Color.Blue else Color.Black,
                text = item.toString(),
                fontSize = 20.sp)
        }
    )
}

@ExperimentalCoilApi
@ExperimentalFoundationApi
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun ExampleDoubleHeaderList(data: List<CustomListItem>){
    DoubleHeaderLazyColumn(data = data,
        modifier = Modifier.fillMaxSize(),
        headerContent = {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                elevation = 5.dp,
                backgroundColor = Color.Red
            ) {
                Text(text = it, fontSize = 20.sp, modifier = Modifier.padding(5.dp))
            }
        },
        subHeaderContent = {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                elevation = 5.dp,
                backgroundColor = Color.Cyan
            ) {
                Text(text = it, fontSize = 17.sp, modifier = Modifier.padding(5.dp))
            }
        },
        itemContent = {
            val item = it as CustomListItem
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
        })
}

// The before-library implementation
//@RequiresApi(Build.VERSION_CODES.N)
//@ExperimentalFoundationApi
//@Composable
//fun MainList(data: List<CustomListItem>) {
//    // Use state to achieve re-compose
//    val listState = rememberLazyListState()
//    val header = remember {
//        mutableStateOf(data[0].type)
//    }
//
//    // Use state to understand if the user is moving to the start or the end of the list
//    val firstItemIndex = remember {
//        mutableStateOf(0)
//    }
//
//    // create a list which is a mirror of the LazyColumn
//    val dataList = mutableListOf<CustomListItem>()
//    val groupHeader = data.groupBy { it.type }
//    groupHeader.forEach { (header, items) ->
//        dataList.add(CustomListItem(type = header))
//        val groupSubHeader = items.groupBy { it.subType }
//        groupSubHeader.forEach { (subheader, items2) ->
//            dataList.add(CustomListItem(type = header, subType = subheader))
//            dataList.addAll(items2)
//        }
//    }
//
//    val mainGroup = data.groupBy { it.type }
//    Column() {
//        // This header's content will be updated while scrolling
//        Header(header = header.value)
//        LazyColumn(state = listState) {
//            mainGroup.forEach { (type, groupedData) ->
//                if (header.value !== type) {
//                    // This header visibility will be updated while scrolling
//                    stickyHeader { Header(header = type) }
//                }
//                val subGroup = groupedData.groupBy { it.subType }
//                subGroup.forEach { (subtype, subGroupedData)->
//                    stickyHeader {
//                        // Create only the subheader item
//                        Header(subheader = "$subtype (${listState.firstVisibleItemIndex})")
//                    }
//
//                    items(items = subGroupedData) {
//                        //listState.firstVisibleItemIndex.toString()
//                        SimpleItem(item = it)
//
//                        /* This is doing the magic for visibility and content of the above headers
//                            depending on the user scrolling action
//                         */
//                        if (listState.firstVisibleItemIndex < firstItemIndex.value) { // move to start
//                            if (listState.firstVisibleItemIndex > 0) {
//                                header.value = dataList[listState.firstVisibleItemIndex - 1].type
//                            }
//                        }
//                        else if (listState.firstVisibleItemIndex > firstItemIndex.value) { // move to the end
//                            /*
//                            When you scroll to start and you go to the point where the stickyHeader should
//                            become visible by default it behaves like scrolling to the end. When this happens
//                            you enter in this else-if block but the firstVisibleItemIndex is increased by 2
//                            (you added a new item, the stickyHeader). So, do the following action whenever
//                            all these things did not happen.
//                             */
//                            if (listState.firstVisibleItemIndex - firstItemIndex.value != 2){
//                                header.value = dataList[listState.firstVisibleItemIndex].type
//                            }
//                        }
//                        firstItemIndex.value = listState.firstVisibleItemIndex
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun SimpleItem(item : CustomListItem) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(5.dp)
//            .clickable { },
//        elevation = 10.dp
//    ) {
//        Row() {
//            Image(
//                painter = rememberImagePainter(
//                    data = item.imageUrl,
//                    builder = {
//                        transformations(CircleCropTransformation())
//                    }
//                ),
//                contentDescription = null,
//                modifier = Modifier
//                    .size(80.dp)
//                    .padding(5.dp)
//            )
//            Column() {
//                Text(text = item.title, fontSize = 15.sp)
//                Text(text = item.description, fontSize = 10.sp)
//            }
//        }
//    }
//}
//
//@Composable
//fun Header(header: String = "", subheader: String = "") {
//    if (header.isNotEmpty()) {
//        Card(
//            modifier = Modifier
//                .fillMaxWidth(),
//            elevation = 5.dp,
//            backgroundColor = Color.Red
//        ) {
//            Text(text = header, fontSize = 20.sp, modifier = Modifier.padding(5.dp))
//        }
//    }
//    if (subheader.isNotEmpty()) {
//        Card(
//            modifier = Modifier
//                .fillMaxWidth(),
//            elevation = 5.dp,
//            backgroundColor = Color.Red
//        ) {
//            Text(text = subheader, fontSize = 20.sp, modifier = Modifier.padding(5.dp))
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    MiltiHeaderListTheme {
//        //MainList()
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun SimpleItemPreview() {
//    MiltiHeaderListTheme {
//        SimpleItem(item = CustomListItem("Movie", "Action",
//            "Shang-Chi and the Legend of the Ten Rings",
//            "Shang-Chi, the master of unarmed weaponry-based Kung Fu, is forced to " +
//                    "confront his past after being drawn into the Ten Rings organization.",
//            "https://threepixelslab.gr/wp-content/uploads/2021/04/Shang-Chi-and-the-Legend-of-the-Ten-Rings.jpg")
//        )
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun HeaderPreview() {
//    Header(header = "header")
//}