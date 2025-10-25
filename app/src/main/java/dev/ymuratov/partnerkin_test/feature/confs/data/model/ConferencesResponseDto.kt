package dev.ymuratov.partnerkin_test.feature.confs.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConferencesResponseDto(
    @SerialName("data") val conferenceData: ConferenceDataDto,
    val error: String?
)