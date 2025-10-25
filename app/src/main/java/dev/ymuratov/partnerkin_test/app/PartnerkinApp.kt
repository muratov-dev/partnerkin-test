package dev.ymuratov.partnerkin_test.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import dev.ymuratov.partnerkin_test.core.ui.navigation.PartnerkinNavHost
import dev.ymuratov.partnerkin_test.core.ui.navigation.Screen

@Composable
fun PartnerkinApp(navController: NavHostController, startDestination: Screen) {
    PartnerkinNavHost(navController = navController, startDestination = startDestination, modifier = Modifier.fillMaxSize())
}