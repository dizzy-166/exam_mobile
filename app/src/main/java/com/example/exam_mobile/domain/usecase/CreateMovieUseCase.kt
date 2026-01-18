package com.example.exam_mobile.domain.usecase

import com.example.exam_mobile.data.dto.MovieRequest
import com.example.exam_mobile.domain.model.Movie
import com.example.exam_mobile.domain.repository.MoviesRepository
import javax.inject.Inject

// Use case для создания фильма
class CreateMovieUseCase @Inject constructor(
    private val repository: MoviesRepository
) {
    // Оператор invoke позволяет вызывать объект как функцию
    suspend operator fun invoke(request: MovieRequest) {
        repository.createMovie(request)
    }
}