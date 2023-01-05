package com.naram.mvi_project.di

import com.naram.data.source.RemoteSource
import com.naram.data.source.RemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindsRemoteSource(dataSource: RemoteSourceImpl): RemoteSource
}