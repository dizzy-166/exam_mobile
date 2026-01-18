package com.example.exam_mobile.presentation.state

import com.example.exam_mobile.domain.model.Movie

// Состояние формы создания/редактирования фильма
data class MovieFormState(
    val title: String = "",
    val description: String = "",
    val ratingKinoPoisk: String = "",
    val ratingIMDB: String = "",
    val genre: String = "",
    val country: String = "",
    val director: String = "",
    val isLoading: Boolean = false, // Флаг загрузки
    val error: String? = null, // Сообщение об ошибке
    val success: Boolean = false, // Флаг успешного сохранения
    val movieId: String? = null // ID фильма (null - создание, не null - редактирование)
)