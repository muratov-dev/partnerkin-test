package dev.ymuratov.partnerkin_test.feature.conf_info.ui.model

import androidx.compose.runtime.Immutable
import dev.ymuratov.partnerkin_test.feature.confs.domain.model.ConferenceInfoModel

@Immutable
data class ConferenceInfoState(
    val conferenceInfo: ConferenceInfoModel? = null,
)
