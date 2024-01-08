package com.learning.persistence

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.learning.persistence.storage.User
import com.learning.persistence.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<TheViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val user by viewModel.user.collectAsState(initial = User())

                    Column {
                        Greeting(user)

                        UserInput(user, viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(user: User, modifier: Modifier = Modifier) {
    Text(
        text = "Hello ${user.firstname} ${user.lastname}!",
        modifier = modifier
    )
}

@Composable
fun UserInput(user: User, viewModel: TheViewModel, modifier: Modifier = Modifier) {
    var firstName by remember { mutableStateOf(TextFieldValue("")) }
    var lastName by remember { mutableStateOf(TextFieldValue("")) }

    TextField(
        value = firstName,
        label = { Text(text = "Firstname") },
        onValueChange = { newText ->
            firstName = newText
            viewModel.updateUserData(user.copy(firstname = newText.text))
        }
    )

    TextField(
        value = lastName,
        label = { Text(text = "Lastname") },
        onValueChange = { newText ->
            lastName = newText
            viewModel.updateUserData(user.copy(lastname = newText.text))
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
//        Greeting("Android", "Google")
    }
}