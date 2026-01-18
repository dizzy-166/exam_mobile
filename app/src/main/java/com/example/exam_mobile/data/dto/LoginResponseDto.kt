package com.example.exam_mobile.data.dto

data class LoginResponseDto(
    val token: String,
    val record: UserRecordDto
)

data class UserRecordDto(
    val id: String,
    val email: String,
    val name: String
)