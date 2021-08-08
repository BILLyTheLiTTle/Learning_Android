package learning.android.compose.ui.screens.main.resume

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import learning.android.compose.ui.theme.JetpackComposeExampleTheme

@Composable
fun ResumeScreen(name: String, option: String) {
    ResumeContent(name = name, option = option)
}

@Composable
fun ResumeContent(name: String, option: String){
    Column(modifier = Modifier.padding(16.dp).fillMaxHeight(1f)) {
        Text(
            text = "Hello, $name your favorite weather is $option Weather",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.h5
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DummyResume() {
    JetpackComposeExampleTheme {
        ResumeContent(name = "Bill", option = "Sunny")
    }
}