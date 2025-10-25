package dev.ymuratov.partnerkin_test.feature.confs.ui.model

import dev.ymuratov.partnerkin_test.feature.confs.domain.model.ConferenceInfoModel
import java.time.YearMonth

data class ConferencesState(
    val isLoading: Boolean = false,
    val groupedConferences: Map<YearMonth, List<ConferenceInfoModel>> = mapOf()
)