package dev.ymuratov.partnerkin_test.feature.confs.data.remote

import dev.ymuratov.partnerkin_test.BuildConfig
import dev.ymuratov.partnerkin_test.feature.confs.data.model.ConferencesResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ConferencesApiService {

    @GET("list")
    suspend fun getConferences(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Response<ConferencesResponseDto>
}