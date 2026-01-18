package com.example.exam_mobile.domain.usecase

import com.example.exam_mobile.domain.model.User
import com.example.exam_mobile.domain.repository.AuthRepository
import javax.inject.Inject

// Use case для авторизации пользователя
class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): User {
        return repository.login(email, password)
    }
}