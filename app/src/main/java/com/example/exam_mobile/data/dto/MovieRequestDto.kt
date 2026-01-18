package com.example.exam_mobile.data.dto

data class MovieRequest(
    val title: String,
    val description: String,
    val genre: String,
    val country: String,
    val director: String,
    val ratingKinoPoisk: Double,
    val ratingIMDB: Double
)