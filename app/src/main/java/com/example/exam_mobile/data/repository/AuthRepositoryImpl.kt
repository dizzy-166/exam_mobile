package com.example.exam_mobile.data.repository

import com.example.exam_mobile.data.local.TokenDataStore
import com.example.exam_mobile.data.remote.AuthApi
import com.example.exam_mobile.data.dto.LoginRequestDto
import com.example.exam_mobile.domain.model.User
import com.example.exam_mobile.domain.repository.AuthRepository
import javax.inject.Inject

// Реализация репозитория для аутентификации
class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi,
    private val tokenDataStore: TokenDataStore
) : AuthRepository {

    // Авторизация пользователя
    override suspend fun login(email: String, password: String): User {
        // Отправляем запрос на сервер
        val response = api.login(
            LoginRequestDto(
                identity = email,
                password = password
            )
        )

        // Сохраняем полученный токен в DataStore
        tokenDataStore.saveToken(response.token)

        // Возвращаем доменную модель пользователя
        return User(
            id = response.record.id,
            email = response.record.email,
            name = response.record.name
        )
    }

    // Выход из аккаунта - удаление токена
    override suspend fun logout() {
        tokenDataStore.clearToken()
    }
}