package learning.android.kmm.android.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.*
import learning.android.kmm.Platform

@Composable
fun SampleGreeting(platform: Platform) {
    Column {
        Text(
            text = "A simple Hello (${platform.name})"
        )
    }
}