package learning.android.kmm.android.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter

@Composable
actual fun NetworkPart(url: String) {
    val painter = rememberAsyncImagePainter(model = url)
    val fallbackPainter = rememberAsyncImagePainter(model = "https://advicesacademy.com/wp-content/uploads/2014/01/Common-Android-Errors.png")
    Column {
        Image(
            painter = if (painter.state is AsyncImagePainter.State.Error) fallbackPainter else painter,
            contentDescription = null,
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
        )
    }
}
