package com.example.exam_mobile.data.repository

import com.example.exam_mobile.data.dto.MovieRequest
import com.example.exam_mobile.data.mapper.toDomain
import com.example.exam_mobile.data.remote.MoviesApi
import com.example.exam_mobile.domain.model.Movie
import com.example.exam_mobile.domain.repository.MoviesRepository
import javax.inject.Inject

// Реализация репозитория для работы с фильмами
class MoviesRepositoryImpl @Inject constructor(
    private val api: MoviesApi
) : MoviesRepository {

    // Получение списка всех фильмов
    override suspend fun getMovies(): List<Movie> {
        return api.getMovies().items.map { it.toDomain() }
    }

    // Получение фильма по ID
    override suspend fun getMovieById(id: String): Movie {
        return api.getMovieById(id).toDomain()
    }

    // Создание нового фильма
    override suspend fun createMovie(request: MovieRequest): Movie {
        return api.createMovie(request).toDomain()
    }

    // Обновление существующего фильма
    override suspend fun updateMovie(id: String, request: MovieRequest): Movie {
        return api.updateMovie(id, request).toDomain()
    }

    // Удаление фильма по ID
    override suspend fun deleteMovie(id: String) {
        val response = api.deleteMovie(id)
        if (!response.isSuccessful) {
            throw Exception("Ошибка при удалении фильма: ${response.code()}")
        }
    }
}