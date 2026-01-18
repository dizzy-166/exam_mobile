package com.example.exam_mobile.domain.model

// Доменная модель фильма
data class Movie(
    val id: String,
    val title: String,
    val description: String,
    val ratingKinoPoisk: Double?,
    val ratingIMDB: Double?,
    val genre: String,
    val country: String,
    val director: String,
    val created: String,
    val updated: String
)