package com.naram.mvi_project.di

import com.naram.domain.RepositoriesUseCase
import com.naram.domain.repo.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun providesRepositoriesUseCase(repository: Repository): RepositoriesUseCase {
        return RepositoriesUseCase(repository)
    }
}