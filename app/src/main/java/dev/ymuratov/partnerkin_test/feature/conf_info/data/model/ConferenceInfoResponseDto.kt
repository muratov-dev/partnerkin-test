package dev.ymuratov.partnerkin_test.feature.conf_info.data.model


import dev.ymuratov.partnerkin_test.feature.confs.data.model.ConferenceInfoDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConferenceInfoResponseDto(
    @SerialName("data") val conferenceInfo: ConferenceInfoDto,
    val error: String?
)