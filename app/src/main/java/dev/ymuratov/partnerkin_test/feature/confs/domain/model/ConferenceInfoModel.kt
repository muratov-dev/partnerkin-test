package dev.ymuratov.partnerkin_test.feature.confs.domain.model


import kotlinx.serialization.Serializable

@Serializable
data class ConferenceInfoModel(
    val categories: List<ConferenceCategoryModel>,
    val city: String,
    val country: String,
    val format: String,
    val id: Int,
    val imageUrl: String?,
    val name: String,
    val startDate: String,
    val status: String,
    val statusTitle: String,
    val type: String,
)