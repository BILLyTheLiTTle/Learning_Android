package learning.android.compose.ui.screens.main.greetings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import learning.android.compose.ui.theme.JetpackComposeExampleTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.material.TextFieldDefaults.textFieldColors
import androidx.compose.ui.graphics.Color

@Composable
fun GreetingsScreen(viewModel: GreetingsScreenViewModel) {
    val name: String by viewModel.name.observeAsState("")
    GreetingsContent(name = name, onNameChange = {viewModel.updateName(it)})
}

@Composable
fun GreetingsContent(name: String, onNameChange: (String) -> Unit){
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Hello, $name",
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.h5
        )
        TextField(
            value = name,
            modifier = Modifier.padding(8.dp),
            onValueChange = onNameChange,
            placeholder = { Text("Name")},
            label = { Text("Enter Name")},
            colors = textFieldColors(backgroundColor = Color.Transparent)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DummyGreetings() {
    JetpackComposeExampleTheme {
        // TODO find a better way to inject ViewModel
        GreetingsScreen(viewModel = GreetingsScreenViewModel())
    }
}