package com.example.exam_mobile.di

import com.example.exam_mobile.data.remote.AuthApi
import com.example.exam_mobile.data.remote.AuthInterceptor
import com.example.exam_mobile.data.remote.MoviesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// Dagger Hilt модуль для сетевых зависимостей
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Базовый URL API (10.0.2.2 - эмулятор Android для localhost)
    private const val BASE_URL = "http://10.0.2.2:8090"

    // Предоставляет интерцептор для логирования запросов/ответов
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // Логировать всё тело запросов
        }
    }

    // Предоставляет настроенный OkHttpClient с интерцепторами
    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor) // Логирование
            .addInterceptor(authInterceptor)    // Добавление токена авторизации
            .build()
    }

    // Предоставляет экземпляр Retrofit
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // JSON конвертер
            .client(okHttpClient)
            .build()
    }

    // Предоставляет API для работы с фильмами
    @Provides
    @Singleton
    fun provideMoviesApi(retrofit: Retrofit): MoviesApi {
        return retrofit.create(MoviesApi::class.java)
    }

    // Предоставляет API для аутентификации
    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }
}