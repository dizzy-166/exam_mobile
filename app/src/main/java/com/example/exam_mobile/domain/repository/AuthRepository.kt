package com.example.exam_mobile.domain.repository

import com.example.exam_mobile.domain.model.User

interface AuthRepository {
    suspend fun login(email: String, password: String): User
    suspend fun logout()
}