package com.mtanmay.data.di

import com.mtanmay.data.datasource.JSONDataSource
import com.mtanmay.data.repository.JSONDataRepositoryImpl
import com.mtanmay.domain.repository.JSONDataRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesJsonDataRepository(datasource: JSONDataSource): JSONDataRepository =
        JSONDataRepositoryImpl(datasource)

}