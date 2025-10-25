package dev.ymuratov.partnerkin_test.feature.confs.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ConferencesContainer(modifier: Modifier = Modifier) {
    ConferencesContent(modifier = modifier)
}

@Composable
private fun ConferencesContent(modifier: Modifier = Modifier) {
    Scaffold(modifier = modifier) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding))
    }
}