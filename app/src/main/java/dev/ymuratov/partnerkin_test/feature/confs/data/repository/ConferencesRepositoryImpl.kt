package dev.ymuratov.partnerkin_test.feature.confs.data.repository

import dev.ymuratov.partnerkin_test.feature.confs.data.model.toDomain
import dev.ymuratov.partnerkin_test.feature.confs.data.remote.ConferencesApiService
import dev.ymuratov.partnerkin_test.feature.confs.domain.model.ConferenceInfoModel
import dev.ymuratov.partnerkin_test.feature.confs.domain.repository.ConferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class ConferencesRepositoryImpl @Inject constructor(
    private val conferencesApiService: ConferencesApiService
) : ConferencesRepository {

    private val _conferencesFlow = MutableStateFlow<List<ConferenceInfoModel>>(emptyList())
    override val conferencesFlow: Flow<List<ConferenceInfoModel>>
        get() = _conferencesFlow.asStateFlow()

    override suspend fun refreshConferences() {
        try {
            val request = conferencesApiService.getConferences()
            if (request.isSuccessful) {
                val body = request.body()?.conferenceData?.result ?: throw IOException("Request body is null")
                val conferences = body.map { conference ->
                    conference.conference.toDomain()
                }
                _conferencesFlow.update { conferences }
            } else {
                throw IOException("Request finished with error code: ${request.code()}")
            }
        } catch (ex: Exception) {
            Timber.d(ex)
        }
    }
}