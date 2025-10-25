package dev.ymuratov.partnerkin_test.feature.confs.ui.model

sealed interface ConferencesAction {

    data object NavigateToInfo: ConferencesAction
}