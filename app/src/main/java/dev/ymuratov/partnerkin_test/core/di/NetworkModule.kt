package dev.ymuratov.partnerkin_test.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.ymuratov.partnerkin_test.BuildConfig
import dev.ymuratov.partnerkin_test.core.data.interceptor.NetworkConnectionInterceptor
import dev.ymuratov.partnerkin_test.feature.confs.data.remote.ConferencesApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val OK_HTTP_TIMEOUT = 10L
    private const val BASE_URL = "https://partnerkin.com/api_ios_test/"

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(if (BuildConfig.DEBUG) Level.BODY else Level.NONE)

    @Singleton
    @Provides
    fun provideOkHttpClient(
        networkInterceptor: NetworkConnectionInterceptor, loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(networkInterceptor).addInterceptor(loggingInterceptor)
            .connectTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS).readTimeout(0, TimeUnit.SECONDS).writeTimeout(0, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitClient(json: Json, okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        val converterFactory = json.asConverterFactory(contentType)
        return Retrofit.Builder().addConverterFactory(converterFactory).baseUrl(BASE_URL).client(okHttpClient).build()
    }

    @Provides
    @Singleton
    fun provideConferencesApiService(retrofit: Retrofit): ConferencesApiService = retrofit.create(ConferencesApiService::class.java)
}