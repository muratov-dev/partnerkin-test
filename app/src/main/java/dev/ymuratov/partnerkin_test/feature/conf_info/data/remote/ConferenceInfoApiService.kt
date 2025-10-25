package dev.ymuratov.partnerkin_test.feature.conf_info.data.remote

import dev.ymuratov.partnerkin_test.BuildConfig
import dev.ymuratov.partnerkin_test.feature.conf_info.data.model.ConferenceInfoResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ConferenceInfoApiService {

    @GET("view")
    suspend fun getConferences(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Response<ConferenceInfoResponseDto>
}