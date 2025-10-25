package dev.ymuratov.partnerkin_test.feature.confs.ui.model

sealed interface ConferencesEvent {

    data object RefreshConferences: ConferencesEvent
    data object OnNavigateToInfo: ConferencesEvent
}