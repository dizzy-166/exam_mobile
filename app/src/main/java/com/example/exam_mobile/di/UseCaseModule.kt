package com.example.exam_mobile.di

import com.example.exam_mobile.domain.repository.AuthRepository
import com.example.exam_mobile.domain.repository.MoviesRepository
import com.example.exam_mobile.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

// Dagger Hilt модуль для use case (сценариев использования)
@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    // Получение списка фильмов
    @Provides
    fun provideGetMoviesUseCase(repository: MoviesRepository) =
        GetMoviesUseCase(repository)

    // Получение фильма по ID
    @Provides
    fun provideGetMovieByIdUseCase(repository: MoviesRepository) =
        GetMovieByIdUseCase(repository)

    // Создание нового фильма
    @Provides
    fun provideCreateMovieUseCase(repository: MoviesRepository) =
        CreateMovieUseCase(repository)

    // Обновление существующего фильма
    @Provides
    fun provideUpdateMovieUseCase(repository: MoviesRepository) =
        UpdateMovieUseCase(repository)

    // Удаление фильма
    @Provides
    fun provideDeleteMovieUseCase(repository: MoviesRepository) =
        DeleteMovieUseCase(repository)

    // Авторизация пользователя
    @Provides
    fun provideLoginUseCase(repository: AuthRepository) =
        LoginUseCase(repository)
}