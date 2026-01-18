package com.example.exam_mobile.data.dto

// DTO для запроса авторизации. Используется для отправки данных логина на сервер.
// identity: идентификатор пользователя
// password: пароль пользователя
data class LoginRequestDto(
    val identity: String,
    val password: String
)