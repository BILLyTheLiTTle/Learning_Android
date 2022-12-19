package learning.android.kmm.android.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import java.io.ByteArrayOutputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.imageio.ImageIO

@Composable
actual fun NetworkPart(url: String) {
    /* It contains network which a bad thing, obviously.
        I would like to experiment with expect/actual for Composables
        and I not gonna hide it I want just to finish this proof of concept project.
        I wish I will not forget to make the changes I have in mind in the near future!
        Also needs remember delegate as well...
     */
    val url = URL(url)
    val connection = url.openConnection() as HttpURLConnection
    connection.connect()

    val inputStream = connection.inputStream
    val bufferedImage = ImageIO.read(inputStream)

    val stream = ByteArrayOutputStream()
    ImageIO.write(bufferedImage, "png", stream)
    val byteArray = stream.toByteArray()

    val bitmap = org.jetbrains.skia.Image.makeFromEncoded(byteArray).toComposeImageBitmap()

    Column {
        Text(text = "Network Response:", style = TextStyle(fontWeight = FontWeight.Bold))

        Image(
            bitmap = bitmap, "", modifier = Modifier.fillMaxWidth()
        )
    }
}
