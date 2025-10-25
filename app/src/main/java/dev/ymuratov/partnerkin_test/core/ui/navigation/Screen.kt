package dev.ymuratov.partnerkin_test.core.ui.navigation

import kotlinx.serialization.Serializable

sealed class Screen {

    @Serializable
    object ConferencesScreen : Screen()

    @Serializable
    object ConferenceInfoScreen : Screen()
}