package com.example.exam_mobile.presentation.state

import com.example.exam_mobile.domain.model.Movie

// Состояние экрана списка фильмов
data class MoviesListState(
    val movies: List<Movie> = emptyList(), // Список фильмов
    val isLoading: Boolean = false, // Флаг загрузки
    val error: String? = null // Сообщение об ошибке
)