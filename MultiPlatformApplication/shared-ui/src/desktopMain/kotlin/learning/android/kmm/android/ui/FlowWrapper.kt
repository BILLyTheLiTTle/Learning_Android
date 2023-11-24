package learning.android.kmm.android.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

@Composable
actual fun <T : R, R> Flow<T>.collectState(
    initial: R,
    context: CoroutineContext
): State<R> = this.collectAsState(initial = initial, context = context)