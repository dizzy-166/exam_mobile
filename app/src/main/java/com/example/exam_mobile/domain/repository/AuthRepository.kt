package com.example.exam_mobile.domain.repository

import com.example.exam_mobile.domain.model.User

// Интерфейс репозитория для аутентификации (слой domain)
interface AuthRepository {
    // Авторизация пользователя
    suspend fun login(email: String, password: String): User

    suspend fun logout()
}