package com.example.exam_mobile.data.dto

// Ответ сервера на успешный логин
data class LoginResponseDto(
    val token: String,
    val record: UserRecordDto
)

// DTO с информацией о пользователе
data class UserRecordDto(
    val id: String,
    val email: String,
    val name: String
)