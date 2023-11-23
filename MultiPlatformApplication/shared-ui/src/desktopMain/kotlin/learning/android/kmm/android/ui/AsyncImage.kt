package learning.android.kmm.android.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.io.ByteArrayOutputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.imageio.ImageIO

@Composable
actual fun NetworkPart(url: String) {
    fun loadImage(source: String): ImageBitmap {
        val url = URL(source)
        val connection = url.openConnection() as HttpURLConnection
        connection.connect()

        val inputStream = connection.inputStream
        val bufferedImage = ImageIO.read(inputStream)

        val stream = ByteArrayOutputStream()
        ImageIO.write(bufferedImage, "png", stream)
        val byteArray = stream.toByteArray()

        return  org.jetbrains.skia.Image.makeFromEncoded(byteArray).toComposeImageBitmap()
    }
    /* It contains network which a bad thing, obviously.
        I would like to experiment with expect/actual for Composables
        and I not gonna hide it I want just to finish this proof of concept project.
        I wish I will not forget to make the changes I have in mind in the near future!
        Also needs remember delegate as well...
        I wish also I could use Kamel library, but ...https://github.com/alialbaali/Kamel/issues/30
     */
    val bitmap = try {
        loadImage(url)
    } catch (_: Exception) {
        loadImage("https://cdn-icons-png.flaticon.com/512/5741/5741333.png")
    }

    Column {

        Image(
            bitmap = bitmap, "", modifier = Modifier.width(100.dp).height(100.dp)
        )
    }
}
