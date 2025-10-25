package dev.ymuratov.partnerkin_test.feature.confs.ui.model

import dev.ymuratov.partnerkin_test.feature.confs.domain.model.ConferenceInfoModel

data class ConferencesState(
    val isLoading: Boolean = false,
    val conferences: List<ConferenceInfoModel> = emptyList()
)