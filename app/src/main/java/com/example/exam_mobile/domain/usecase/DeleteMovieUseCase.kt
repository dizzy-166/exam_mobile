package com.example.exam_mobile.domain.usecase

import com.example.exam_mobile.domain.repository.MoviesRepository
import javax.inject.Inject

// Use case для удаления фильма
class DeleteMovieUseCase @Inject constructor(
    private val repository: MoviesRepository
) {
    suspend operator fun invoke(id: String) {
        repository.deleteMovie(id)
    }
}