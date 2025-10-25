package dev.ymuratov.partnerkin_test.feature.confs.domain.repository

import dev.ymuratov.partnerkin_test.feature.confs.domain.model.ConferenceInfoModel
import kotlinx.coroutines.flow.Flow

interface ConferencesRepository {

    val conferencesFlow: Flow<List<ConferenceInfoModel>>

    suspend fun refreshConferences()
}