package com.example.exam_mobile.di

import com.example.exam_mobile.data.repository.AuthRepositoryImpl
import com.example.exam_mobile.data.repository.MoviesRepositoryImpl
import com.example.exam_mobile.domain.repository.AuthRepository
import com.example.exam_mobile.domain.repository.MoviesRepository
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
    abstract fun bindMoviesRepository(
        impl: MoviesRepositoryImpl
    ): MoviesRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository
}