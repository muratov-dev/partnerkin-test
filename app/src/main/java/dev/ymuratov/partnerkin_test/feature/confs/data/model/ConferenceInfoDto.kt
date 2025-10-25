package dev.ymuratov.partnerkin_test.feature.confs.data.model


import dev.ymuratov.partnerkin_test.feature.confs.domain.model.ConferenceInfoModel
import dev.ymuratov.partnerkin_test.feature.confs.domain.model.ConferenceStatusModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class ConferenceInfoDto(
    val categories: List<ConferenceCategoryDto>,
    val city: String,
    @SerialName("city_id") val cityId: Int,
    val country: String,
    @SerialName("country_id") val countryId: Int,
    @SerialName("custom_date") val customDate: String?,
    @SerialName("end_date") val endDate: String,
    val format: String,
    val id: Int,
    val image: ConferenceImageDto,
    val name: String,
    val oneday: Int,
    val rating: String?,
    @SerialName("start_date") val startDate: String,
    val status: String,
    @SerialName("status_title") val statusTitle: String,
    val type: ConferenceTypeDto,
    @SerialName("type_id") val typeId: Int,
    val url: String,
    val about: String? = null,
    @SerialName("register_url") val registerUrl: String? = null,
)

fun ConferenceInfoDto.toDomain(): ConferenceInfoModel {
    return ConferenceInfoModel(
        categories = categories.map { it.toDomain() },
        city = city,
        country = country,
        endDate = LocalDate.parse(endDate),
        format = format,
        id = id,
        imageUrl = image.url,
        name = name,
        startDate = LocalDate.parse(startDate),
        status = ConferenceStatusModel.from(status),
        statusTitle = statusTitle,
        type = type.name,
        about = about,
        registerUrl = registerUrl
    )
}