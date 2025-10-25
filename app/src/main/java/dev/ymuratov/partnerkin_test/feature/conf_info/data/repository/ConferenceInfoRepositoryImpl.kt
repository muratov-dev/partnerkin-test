package dev.ymuratov.partnerkin_test.feature.conf_info.data.repository

import dev.ymuratov.partnerkin_test.feature.conf_info.data.remote.ConferenceInfoApiService
import dev.ymuratov.partnerkin_test.feature.conf_info.domain.repository.ConferenceInfoRepository
import dev.ymuratov.partnerkin_test.feature.confs.data.model.toDomain
import dev.ymuratov.partnerkin_test.feature.confs.domain.model.ConferenceInfoModel
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class ConferenceInfoRepositoryImpl @Inject constructor(
    private val conferenceInfoApiService: ConferenceInfoApiService
) : ConferenceInfoRepository {

    override suspend fun getConferenceInfo(): Result<ConferenceInfoModel> {
        try {
            val request = conferenceInfoApiService.getConferences()
            if (request.isSuccessful) {
                val body = request.body()?.conferenceInfo ?: throw IOException("Request body is null")
                val conferenceInfo = body.toDomain()
                return Result.success(conferenceInfo)
            } else {
                throw IOException("Request finished with error code: ${request.code()}")
            }
        } catch (ex: Exception) {
            Timber.d(ex)
        }
        return Result.failure(IOException("Something went wrong"))
    }
}