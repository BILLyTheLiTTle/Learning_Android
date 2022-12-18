package learning.android.kmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import learning.android.kmm.AndroidPlatform
import learning.android.kmm.android.ui.SampleGreeting
import learning.android.kmm.db.Repository
import learning.android.kmm.network.NetworkAction
import learning.android.kmm.network.NetworkActionImpl
import learning.android.kmm.network.settings.HttpClientSetup
import learning.android.kmm.network.settings.JsonSerializerSetup
import org.koin.android.ext.android.inject
import learning.android.kmm.Greeting as GreetingFunctionality

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors(
            primary = Color(0xFFBB86FC),
            primaryVariant = Color(0xFF3700B3),
            secondary = Color(0xFF03DAC5)
        )
    } else {
        lightColors(
            primary = Color(0xFF6200EE),
            primaryVariant = Color(0xFF3700B3),
            secondary = Color(0xFF03DAC5)
        )
    }
    val typography = Typography(
        body1 = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

class MainActivity : ComponentActivity() {

    private val greeting: GreetingFunctionality by inject()
    private val network: NetworkAction by inject()
    private val repository: Repository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    repository.resetData()
//                    repository.addData(0, "")
//                    Greeting(greeting, network, repository)
                    SampleGreeting(AndroidPlatform())
                }
            }
        }
    }
}

@Composable
fun Greeting(
    greeting: GreetingFunctionality,
    networkAction: NetworkAction,
    repository: Repository
) {
    var text by remember { mutableStateOf("") }
    var response by remember { mutableStateOf("https://www.elegantthemes.com/blog/wp-content/uploads/2022/01/lazy-loading.png") }
    val painter = rememberAsyncImagePainter(model = response)
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
            text = "${greeting.greeting(text)} ${
                if (text.isNotEmpty()) {
                    "(Flavor: ${BuildConfig.FLAVOR_NAME})"
                } else {
                    ""
                }
            }"
        )

        Spacer(modifier = Modifier.padding(10.dp))
        
        NetworkPart(painter = painter)
        
        DbPart(dbContent = dbText.value, dbVersion = dbVersion.value)
        
        LaunchedEffect(Unit) {
            response = networkAction.getDogImageUrl().message ?: ""
        }
    }
}

@Composable
private fun NetworkPart(painter: AsyncImagePainter) {
    Column {
        Text(text = "Network Response:", style = TextStyle(fontWeight = FontWeight.Bold))

        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun DbPart(dbContent: String, dbVersion: String) {
    Column {
        Text(text = "DB Response: (DB Version: $dbVersion)", style = TextStyle(fontWeight = FontWeight.Bold))

        Text(text = dbContent)
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Greeting(
            greeting = GreetingFunctionality(AndroidPlatform()),
            networkAction = NetworkActionImpl(HttpClientSetup(JsonSerializerSetup())),
            repository = Repository()
        )
    }
}
