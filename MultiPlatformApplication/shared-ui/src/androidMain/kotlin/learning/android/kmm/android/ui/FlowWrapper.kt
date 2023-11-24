package learning.android.kmm.android.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

@Composable
actual fun <T : R, R> Flow<T>.collectState(
    initial: R,
    context: CoroutineContext
): State<R> = this.collectAsStateWithLifecycle(initialValue = initial, context = context)