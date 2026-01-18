package com.example.exam_mobile.domain.usecase

import com.example.exam_mobile.domain.model.Movie
import com.example.exam_mobile.domain.repository.MoviesRepository
import javax.inject.Inject

// Use case для получения списка фильмов
class GetMoviesUseCase @Inject constructor(
    private val repository: MoviesRepository
) {
    suspend operator fun invoke(): List<Movie> {
        return repository.getMovies()
    }
}