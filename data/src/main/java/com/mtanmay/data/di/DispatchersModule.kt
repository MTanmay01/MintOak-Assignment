package com.mtanmay.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
class DispatchersModule {

    @Dispatcher(DispatcherType.IO)
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Dispatcher(DispatcherType.MAIN)
    @Provides
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Dispatcher(DispatcherType.DEFAULT)
    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class Dispatcher(val type: DispatcherType)

    enum class DispatcherType {
        IO, MAIN, DEFAULT
    }
}