package learning.android.dogbreeds.ui.widgets.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation
import learning.android.dogbreeds.R
import learning.android.dogbreeds.ui.theme.CareysPink
import learning.android.dogbreeds.ui.theme.Mystic

@Composable
fun CircularImage(imgUrl: String, modifier: Modifier) {
    Surface(
        elevation = 5.dp,
        color = Mystic,
        shape = CircleShape,
        modifier = Modifier
            .padding(horizontal = 1.dp, vertical = 4.dp)
            .border(2.dp, CareysPink, CircleShape)
    ) {
        Image(
            painter = rememberImagePainter(
                data = imgUrl,
                builder = {
                    // These do not achieve my goal (I want to see the dog image without waiting when scroll up)
                    memoryCachePolicy(CachePolicy.ENABLED)
                    diskCachePolicy(CachePolicy.ENABLED)
                    networkCachePolicy(CachePolicy.ENABLED)

                    placeholder(R.drawable.placeholder_dog)
                    error(R.drawable.error)

                    transformations(CircleCropTransformation())
                }
            ),
            contentDescription = null,
            modifier = modifier
                .padding(3.dp)
                .semantics { contentDescription = "A circular image" }
        )
    }
}