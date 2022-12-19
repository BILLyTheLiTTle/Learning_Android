package learning.android.kmm.android.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import coil.compose.rememberAsyncImagePainter

@Composable
actual fun NetworkPart(url: String) {
    val painter = rememberAsyncImagePainter(model = url)
    Column {
        Text(text = "Network Response:", style = TextStyle(fontWeight = FontWeight.Bold))

        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
