package com.example.exam_mobile.presentation.state

import com.example.exam_mobile.domain.model.Movie

// Состояние экрана деталей фильма
data class MovieDetailsState(
    val movie: Movie? = null, // Данные фильма (null если не загружены)
    val isLoading: Boolean = false, // Флаг загрузки
    val error: String? = null, // Сообщение об ошибке
    val deleteSuccess: Boolean = false // Флаг успешного удаления
)