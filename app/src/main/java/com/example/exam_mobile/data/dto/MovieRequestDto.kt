package com.example.exam_mobile.data.dto

// DTO для создания или обновления фильма (запрос к серверу)
data class MovieRequest(
    val title: String,
    val description: String,
    val genre: String,
    val country: String,
    val director: String,
    val ratingKinoPoisk: Double,
    val ratingIMDB: Double
)