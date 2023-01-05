package com.naram.mvi_project.di

import com.naram.data.RepositoryImpl
import com.naram.domain.repo.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsRepository(repository: RepositoryImpl): Repository
}