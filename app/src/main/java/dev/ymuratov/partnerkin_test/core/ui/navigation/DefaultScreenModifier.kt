package dev.ymuratov.partnerkin_test.core.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.ymuratov.partnerkin_test.core.ui.theme.PartnerkinTheme

@Composable
fun Modifier.defaultModifier(): Modifier = fillMaxSize()
    .background(PartnerkinTheme.colors.backgroundPrimary)
    .systemBarsPadding()
