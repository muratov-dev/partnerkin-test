package dev.ymuratov.partnerkin_test.feature.confs.data.model


import dev.ymuratov.partnerkin_test.feature.confs.domain.model.ConferenceCategoryModel
import kotlinx.serialization.Serializable

@Serializable
data class ConferenceCategoryDto(
    val id: Int, val name: String, val url: String
)

fun ConferenceCategoryDto.toDomain(): ConferenceCategoryModel = ConferenceCategoryModel(id = id, name = name)