package dev.ymuratov.partnerkin_test.app

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dev.ymuratov.partnerkin_test.core.ui.navigation.Screen
import dev.ymuratov.partnerkin_test.core.ui.theme.PartnerkinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
        )
        setContent {
            val navController = rememberNavController()
            val startDestination = Screen.ConferencesScreen
            PartnerkinTheme {
                PartnerkinApp(navController, startDestination = startDestination)
            }
        }
    }
}