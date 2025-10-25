package dev.ymuratov.partnerkin_test.core.ui.component.image

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.ymuratov.partnerkin_test.core.ui.utils.shimmerOnContentLoadingAnimation

@Composable
fun ImageShimmer(modifier: Modifier = Modifier) {
    Box(modifier.shimmerOnContentLoadingAnimation())
}