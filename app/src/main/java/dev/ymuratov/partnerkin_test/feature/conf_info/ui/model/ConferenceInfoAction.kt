package dev.ymuratov.partnerkin_test.feature.conf_info.ui.model

sealed interface ConferenceInfoAction {

    data object NavigateBack : ConferenceInfoAction
}