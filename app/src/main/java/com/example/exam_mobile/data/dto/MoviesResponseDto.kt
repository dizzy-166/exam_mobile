package com.example.exam_mobile.data.dto

data class MoviesResponseDto(
    val page: Int,
    val perPage: Int,
    val totalPages: Int,
    val totalItems: Int,
    val items: List<MovieDto>
)