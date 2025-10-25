package dev.ymuratov.partnerkin_test.feature.confs.data.model


import kotlinx.serialization.Serializable

@Serializable
data class ConferenceTypeDto(
    val id: Int,
    val name: String
)