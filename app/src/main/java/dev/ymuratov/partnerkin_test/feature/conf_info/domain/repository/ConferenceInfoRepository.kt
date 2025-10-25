package dev.ymuratov.partnerkin_test.feature.conf_info.domain.repository

import dev.ymuratov.partnerkin_test.feature.confs.domain.model.ConferenceInfoModel

interface ConferenceInfoRepository {

    suspend fun getConferenceInfo(): Result<ConferenceInfoModel>
}