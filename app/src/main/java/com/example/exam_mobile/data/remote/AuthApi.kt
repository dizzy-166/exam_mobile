package com.example.exam_mobile.data.remote

import com.example.exam_mobile.data.dto.LoginRequestDto
import com.example.exam_mobile.data.dto.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("api/collections/users/auth-with-password")
    suspend fun login(
        @Body request: LoginRequestDto
    ): LoginResponseDto
}