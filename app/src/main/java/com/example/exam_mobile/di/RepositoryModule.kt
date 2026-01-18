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

// Dagger Hilt модуль для репозиториев (использует абстрактный класс и @Binds)
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    // Связывает реализацию MoviesRepositoryImpl с интерфейсом MoviesRepository
    @Binds
    @Singleton
    abstract fun bindMoviesRepository(
        impl: MoviesRepositoryImpl
    ): MoviesRepository

    // Связывает реализацию AuthRepositoryImpl с интерфейсом AuthRepository
    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository
}