package dev.ymuratov.partnerkin_test.feature.confs.domain.model

enum class ConferenceStatusModel {
    PUBLISH, CANCELED;

    companion object {
        fun from(value: String): ConferenceStatusModel{
            return ConferenceStatusModel.entries.find { it.name.equals(value, true) } ?: throw RuntimeException("Unknown status")
        }
    }
}