package dev.ymuratov.partnerkin_test.feature.conf_info.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ConferenceInfoContainer(modifier: Modifier = Modifier) {
    ConferenceInfoContent(modifier = modifier)
}

@Composable
private fun ConferenceInfoContent(modifier: Modifier = Modifier) {
    Scaffold(modifier = modifier) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding))
    }
}