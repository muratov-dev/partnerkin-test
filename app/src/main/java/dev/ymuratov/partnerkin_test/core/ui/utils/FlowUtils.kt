package dev.ymuratov.partnerkin_test.core.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

@Suppress("ComposableNaming")
@Composable
fun <T> Flow<T>.collectFlowWithLifecycle(
    vararg keys: Any?,
    lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    collector: suspend CoroutineScope.(T) -> Unit,
) {
    val flow = this
    val currentCollector by rememberUpdatedState(collector)

    LaunchedEffect(flow, lifecycle, minActiveState, *keys) {
        withContext(Dispatchers.Main.immediate) {
            lifecycle.repeatOnLifecycle(minActiveState) {
                flow.collect { currentCollector(it) }
            }
        }
    }
}
