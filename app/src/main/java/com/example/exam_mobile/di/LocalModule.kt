package com.example.exam_mobile.di

import android.content.Context
import com.example.exam_mobile.data.local.TokenDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Dagger Hilt модуль для зависимостей локального хранилища
@Module
@InstallIn(SingletonComponent::class) // Зависимости живут всё время жизни приложения
object LocalModule {

    // Предоставляет экземпляр TokenDataStore
    @Provides
    @Singleton
    fun provideTokenDataStore(
        @ApplicationContext context: Context // Контекст приложения из Hilt
    ): TokenDataStore {
        return TokenDataStore(context)
    }
}