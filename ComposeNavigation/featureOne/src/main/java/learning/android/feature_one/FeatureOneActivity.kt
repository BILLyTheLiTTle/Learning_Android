package learning.android.feature_one

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import learning.android.feature_one.ui.theme.ComposeNavigationTheme

class FeatureOneActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNavigationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    FeatureOneHome()
                }
            }
        }
    }
}

@Composable
fun FeatureOneHome(welcomeText: String = "", originText: String = "") {
    Column() {
        Text(text = "$welcomeText. You came from $originText")

        Button(onClick = { /*TODO*/ }) {
            Text(text = "Feature 1.1")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Feature 1.2")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Feature 1.3")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeNavigationTheme {
        FeatureOneHome()
    }
}