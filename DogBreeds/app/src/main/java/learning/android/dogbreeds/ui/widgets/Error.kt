package learning.android.dogbreeds.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
fun Error(msg: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.clickable(onClick = onClick)) {
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