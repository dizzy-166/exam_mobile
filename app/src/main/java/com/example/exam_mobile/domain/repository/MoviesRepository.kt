package com.example.exam_mobile.domain.repository

import com.example.exam_mobile.data.dto.MovieRequest
import com.example.exam_mobile.domain.model.Movie

// Интерфейс репозитория для работы с фильмами (слой domain)
interface MoviesRepository {
    // Получить все фильмы
    suspend fun getMovies(): List<Movie>

    // Получить фильм по ID
    suspend fun getMovieById(id: String): Movie

    // Создать новый фильм
    suspend fun createMovie(request: MovieRequest): Movie

    // Обновить существующий фильм
    suspend fun updateMovie(id: String, request: MovieRequest): Movie

    // Удалить фильм по ID
    suspend fun deleteMovie(id: String)
}