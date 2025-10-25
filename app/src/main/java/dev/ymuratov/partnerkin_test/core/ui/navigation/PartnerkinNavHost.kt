package dev.ymuratov.partnerkin_test.core.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.ymuratov.partnerkin_test.feature.conf_info.ui.screen.ConferenceInfoContainer
import dev.ymuratov.partnerkin_test.feature.confs.ui.screen.ConferencesContainer

@Composable
fun PartnerkinNavHost(navController: NavHostController, startDestination: Screen, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = startDestination, modifier = modifier) {
        composable<Screen.ConferencesScreen> {
            ConferencesContainer(
                modifier = Modifier.defaultModifier(), navigateToInfo = { navController.navigate(Screen.ConferenceInfoScreen) })
        }
        composable<Screen.ConferenceInfoScreen> {
            ConferenceInfoContainer(
                modifier = Modifier.defaultModifier(), navigateBack = { navController.navigateUp() })
        }
    }
}