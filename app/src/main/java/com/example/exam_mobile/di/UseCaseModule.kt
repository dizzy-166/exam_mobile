package com.example.exam_mobile.di

import com.example.exam_mobile.domain.repository.AuthRepository
import com.example.exam_mobile.domain.repository.MoviesRepository
import com.example.exam_mobile.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetMoviesUseCase(repository: MoviesRepository) =
        GetMoviesUseCase(repository)

    @Provides
    fun provideGetMovieByIdUseCase(repository: MoviesRepository) =
        GetMovieByIdUseCase(repository)

    @Provides
    fun provideCreateMovieUseCase(repository: MoviesRepository) =
        CreateMovieUseCase(repository)

    @Provides
    fun provideUpdateMovieUseCase(repository: MoviesRepository) =
        UpdateMovieUseCase(repository)

    @Provides
    fun provideDeleteMovieUseCase(repository: MoviesRepository) =
        DeleteMovieUseCase(repository)

    @Provides
    fun provideLoginUseCase(repository: AuthRepository) =
        LoginUseCase(repository)
}