package dev.ymuratov.partnerkin_test.feature.confs.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConferenceImageDto(
    val height: Int,
    val id: String,
    @SerialName("placeholder_color")
    val placeholderColor: String?,
    val preview: String,
    val url: String,
    val width: Int
)