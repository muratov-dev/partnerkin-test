package dev.ymuratov.partnerkin_test.feature.confs.domain.model

import java.time.LocalDate

data class ConferenceInfoModel(
    val categories: List<ConferenceCategoryModel>,
    val city: String,
    val country: String,
    val endDate: LocalDate,
    val format: String,
    val id: Int,
    val imageUrl: String?,
    val name: String,
    val startDate: LocalDate,
    val status: ConferenceStatusModel,
    val statusTitle: String,
    val type: String,
)