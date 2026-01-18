package com.example.exam_mobile.domain.usecase

import com.example.exam_mobile.data.dto.MovieRequest
import com.example.exam_mobile.domain.model.Movie
import com.example.exam_mobile.domain.repository.MoviesRepository
import javax.inject.Inject

class UpdateMovieUseCase @Inject constructor(
    private val repository: MoviesRepository
) {
    suspend operator fun invoke(id: String, request: MovieRequest) {
        repository.updateMovie(id, request)
    }
}
