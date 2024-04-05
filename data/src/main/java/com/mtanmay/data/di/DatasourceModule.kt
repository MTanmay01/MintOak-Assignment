package com.mtanmay.data.di

import android.content.Context
import com.mtanmay.data.datasource.JSONDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatasourceModule {

    @Provides
    @Singleton
    fun providesJsonDataSource(
        @ApplicationContext context: Context,
        @DispatchersModule.Dispatcher(
            DispatchersModule.DispatcherType.IO) ioDispatcher: CoroutineDispatcher
    ): JSONDataSource =
        JSONDataSource(context, ioDispatcher)
}