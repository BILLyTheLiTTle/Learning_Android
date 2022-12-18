package learning.android.kmm.android.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import learning.android.kmm.Greeting
import learning.android.kmm.db.Repository
import learning.android.kmm.network.NetworkAction
import learning.android.kmm.network.NetworkActionImpl
import learning.android.kmm.network.settings.HttpClientSetup
import learning.android.kmm.network.settings.JsonSerializerSetup

@Composable
fun Greeting(
    greeting: Greeting,
    networkAction: NetworkAction,
    repository: Repository
) {
    var text by remember { mutableStateOf("") }
    var response by remember { mutableStateOf("https://www.elegantthemes.com/blog/wp-content/uploads/2022/01/lazy-loading.png") }
//    val resource = lazyPainterResource(data = response)
    val dbText = repository.getData(0).collectAsState(initial = "")
    val dbVersion = repository.getVersion().collectAsState(initial = "")

    Column {
        TextField(
            value = text,
            onValueChange = {
                text = it
                repository.updateData(0, it)
            },
            label = {
                Text(text = "Enter name")
            }
        )
        Text(
            text = "${greeting.greeting(text)}"
        )

        Spacer(modifier = Modifier.padding(10.dp))

//        NetworkPart(painter = painter)

        DbPart(dbContent = dbText.value, dbVersion = dbVersion.value)

        LaunchedEffect(Unit) {
            response = networkAction.getDogImageUrl().message ?: ""
        }
    }
}

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