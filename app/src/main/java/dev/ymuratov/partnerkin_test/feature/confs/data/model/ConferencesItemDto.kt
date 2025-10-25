package dev.ymuratov.partnerkin_test.feature.confs.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConferencesItemDto(
    val conference: ConferenceInfoDto,
    @SerialName("view_type")
    val viewType: Int
)