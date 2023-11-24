package learning.android.kmm.android.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@Composable
expect fun <T : R, R> Flow<T>.collectState(
    initial: R,
    context: CoroutineContext = EmptyCoroutineContext
): State<R>