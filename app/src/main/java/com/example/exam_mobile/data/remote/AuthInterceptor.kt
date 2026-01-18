package com.example.exam_mobile.data.remote

import com.example.exam_mobile.data.local.TokenDataStore
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

// Interceptor для добавления токена авторизации в заголовки запросов
class AuthInterceptor @Inject constructor(
    private val tokenDataStore: TokenDataStore
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        // Получаем токен из DataStore (runBlocking для синхронного вызова suspend функции)
        val token = runBlocking {
            tokenDataStore.getToken()
        }

        val request = if (token.isNullOrEmpty()) {
            // Если токена нет, отправляем запрос без заголовка Authorization
            chain.request()
        } else {
            // Добавляем токен в заголовок Authorization в формате Bearer токен
            chain.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        }

        return chain.proceed(request)
    }
}