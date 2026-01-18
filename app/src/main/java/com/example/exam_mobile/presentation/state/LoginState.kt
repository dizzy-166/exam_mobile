package com.example.exam_mobile.presentation.state

// Состояние экрана входа (Login)
data class LoginState(
    val email: String = "", // Email пользователя
    val password: String = "", // Пароль пользователя
    val isLoading: Boolean = false, // Флаг загрузки
    val error: String? = null // Сообщение об ошибке (null если нет ошибки)
)