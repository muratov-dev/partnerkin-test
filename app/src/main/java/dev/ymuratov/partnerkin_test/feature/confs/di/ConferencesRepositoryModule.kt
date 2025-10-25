package dev.ymuratov.partnerkin_test.feature.confs.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.ymuratov.partnerkin_test.feature.confs.data.repository.ConferencesRepositoryImpl
import dev.ymuratov.partnerkin_test.feature.confs.domain.repository.ConferencesRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ConferencesRepositoryModule {

    @Singleton
    @Binds
    abstract fun bindConferencesRepository(repository: ConferencesRepositoryImpl): ConferencesRepository
}
