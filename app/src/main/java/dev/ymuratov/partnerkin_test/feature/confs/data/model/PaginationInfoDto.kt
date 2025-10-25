package dev.ymuratov.partnerkin_test.feature.confs.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaginationInfoDto(
    @SerialName("current_page")
    val currentPage: Int,
    @SerialName("page_size")
    val pageSize: Int
)