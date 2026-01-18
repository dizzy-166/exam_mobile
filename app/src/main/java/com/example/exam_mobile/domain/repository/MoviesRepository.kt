package com.example.exam_mobile.domain.repository

import com.example.exam_mobile.data.dto.MovieRequest
import com.example.exam_mobile.domain.model.Movie

interface MoviesRepository {
    suspend fun getMovies(): List<Movie>
    suspend fun getMovieById(id: String): Movie

    suspend fun createMovie(request: MovieRequest): Movie
    suspend fun updateMovie(id: String, request: MovieRequest): Movie

    suspend fun deleteMovie(id: String)
}
