package dev.ymuratov.partnerkin_test.feature.confs.data.model


import kotlinx.serialization.Serializable

@Serializable
data class ConferenceDataDto(
    val counts: Int,
    val pagination: PaginationInfoDto,
    val result: List<ConferencesItemDto>
)