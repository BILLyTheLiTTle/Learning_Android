package learning.android.dogbreeds.ui.widgets.list.viewstates

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import learning.android.dogbreeds.R

@Composable
fun ErrorListItem(modifier: Modifier = Modifier, msg: String, onClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier.background(Color.Red).clickable(onClick = onClick)) {
        Image(
            painter = rememberImagePainter(
                data = R.drawable.error,
                builder = { transformations(CircleCropTransformation()) }
            ),
            contentDescription = null,
            modifier = Modifier
                .padding(4.dp)
                .size(70.dp)
        )
        Text(text = msg)
    }
}