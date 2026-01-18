package com.example.exam_mobile.data.repository

import com.example.exam_mobile.data.local.TokenDataStore
import com.example.exam_mobile.data.remote.AuthApi
import com.example.exam_mobile.data.dto.LoginRequestDto
import com.example.exam_mobile.domain.model.User
import com.example.exam_mobile.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi,
    private val tokenDataStore: TokenDataStore
) : AuthRepository {

    override suspend fun login(email: String, password: String): User {
        val response = api.login(
            LoginRequestDto(
                identity = email,
                password = password
            )
        )

        tokenDataStore.saveToken(response.token)

        return User(
            id = response.record.id,
            email = response.record.email,
            name = response.record.name
        )
    }

    override suspend fun logout() {
        tokenDataStore.clearToken()
    }
}