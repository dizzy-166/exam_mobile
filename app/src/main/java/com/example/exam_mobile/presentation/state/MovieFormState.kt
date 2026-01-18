package com.example.exam_mobile.presentation.state

import com.example.exam_mobile.domain.model.Movie

data class MovieFormState(
    val title: String = "",
    val description: String = "",
    val ratingKinoPoisk: String = "",
    val ratingIMDB: String = "",
    val genre: String = "",
    val country: String = "",
    val director: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val success: Boolean = false,
    val movieId: String? = null // null = создание, не null = редактирование
) {
    fun toMap() = mapOf(
        "title" to title,
        "description" to description,
        "ratingKinoPoisk" to ratingKinoPoisk.toDoubleOrNull(),
        "ratingIMDB" to ratingIMDB.toDoubleOrNull(),
        "genre" to genre,
        "country" to country,
        "director" to director
    )
}