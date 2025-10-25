package dev.ymuratov.partnerkin_test.feature.conf_info.ui.model

sealed interface ConferenceInfoEvent {

    data object GetConferenceInfo: ConferenceInfoEvent
}