package learning.android.dogbreeds.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Loading(modifier: Modifier = Modifier) {
    Box(contentAlignment = Alignment.Center, modifier = modifier.background(Color.White)) {
        CircularProgressIndicator(modifier = Modifier.size(100.dp))
    }
}