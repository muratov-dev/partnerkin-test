package dev.ymuratov.partnerkin_test.feature.conf_info.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.ymuratov.partnerkin_test.feature.conf_info.data.repository.ConferenceInfoRepositoryImpl
import dev.ymuratov.partnerkin_test.feature.conf_info.domain.repository.ConferenceInfoRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ConferenceInfoRepositoryModule {

    @Singleton
    @Binds
    abstract fun bindConferenceInfoRepository(repository: ConferenceInfoRepositoryImpl): ConferenceInfoRepository
}
