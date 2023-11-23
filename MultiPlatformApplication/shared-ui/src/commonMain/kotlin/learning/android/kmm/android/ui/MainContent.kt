package learning.android.kmm.android.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import learning.android.kmm.Greeting
import learning.android.kmm.db.Repository
import learning.android.kmm.network.NetworkAction
import learning.android.kmm.network.NetworkState
import learning.android.kmm.network.model.Pet

@Composable
fun MainScreen(
    greeting: Greeting,
    networkAction: NetworkAction,
    repository: Repository
) {
    var proceed by remember { mutableStateOf(false) }
    val dbText = repository.getData(0).collectAsState(initial = "")
    if (dbText.value.isNullOrEmpty()) {
        SplashDialogs(repository)
    } else {
        if (!proceed) {
            Column {
                Text(text = greeting.greeting(dbText.value))
//                Text(text = "DB Schema used: ${repository.getVersion().collectAsState(initial = "0.0.0").value}")
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        proceed = true
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Continue")
                }
            }
        }
    }
    if (proceed) {
        Animals(networkAction)
    }
}

@Composable
fun Animals(networkAction: NetworkAction) {
    var status by remember { mutableStateOf(0) }
    var networkResponse: NetworkState<List<Pet>> by remember { mutableStateOf(NetworkState.Success(emptyList())) }
    println("TSAP")
    LaunchedEffect(Unit) {
        networkResponse = networkAction.getPetData()
        if (networkResponse is NetworkState.Success) {
            status = 1
        } else {
            status = 2
        }
    }

    when (status) {
        0 -> {
            println("TSAP 0")
        }
        1 -> {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items((networkResponse as NetworkState.Success).data as List<Pet>) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        shape = RoundedCornerShape(10.dp),
                        elevation = 5.dp,
                        backgroundColor = Color.Cyan
                    ) {
                        Row(
                            Modifier.padding(10.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            NetworkPart("https://cdn2.thedogapi.com/images/${it.photo ?: ""}.jpg")
                            Text(it.name ?: "", Modifier.padding(5.dp))
                        }
                    }
                }
            }
        }
        else -> {
            NetworkPart(url = (networkResponse as NetworkState.Error).errorIcon)
            println("TSAP 2")
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SplashDialogs(
    repository: Repository
) {
    var name by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    var showDialog by remember { mutableStateOf(true) }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },
            dismissButton = {
                // Create a Cancel button
                Button(
                    onClick = {
                        showDialog = false
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Cancel,
                        contentDescription = null,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Cancel")
                }
            },
            confirmButton = {
                // Create an OK button
                Button(
                    onClick = {
                        showDialog = false
                        // Handle the entered name
                        coroutineScope.launch {
                            // Your logic with the entered name
                            // For now, just printing it
                            repository.updateData(0, name)
                        }
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "OK")
                }
            },
            text = {
                TextField(
                    value = name,
                    onValueChange = {
                        name = it
                    },
                    label = {
                        Text(text = "Enter name")
                    }
                )
            }

        )
    }
}

@Composable
expect fun NetworkPart(url: String)

//@Composable
//private fun NetworkPart(resource: Resource<Painter>) {
//    Column {
//        Text(text = "Network Response:", style = TextStyle(fontWeight = FontWeight.Bold))
//
//        KamelImage(
//            resource = resource,
//            contentDescription = null,
//            modifier = Modifier.fillMaxWidth()
//        )
//    }
//}

@Composable
private fun DbPart(dbContent: String, dbVersion: String) {
    Column {
        Text(text = "DB Response: (DB Version: $dbVersion)", style = TextStyle(fontWeight = FontWeight.Bold))

        Text(text = dbContent)
    }
}