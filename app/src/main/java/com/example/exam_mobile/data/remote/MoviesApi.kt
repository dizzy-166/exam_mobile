package com.example.exam_mobile.data.remote

import com.example.exam_mobile.data.dto.MovieDto
import com.example.exam_mobile.data.dto.MovieRequest
import com.example.exam_mobile.data.dto.MoviesResponseDto
import retrofit2.http.*

// Retrofit интерфейс для работы с API фильмов
interface MoviesApi {

    // GET запрос для получения списка всех фильмов
    @GET("api/collections/movies/records")
    suspend fun getMovies(): MoviesResponseDto

    // GET запрос для получения фильма по ID
    @GET("api/collections/movies/records/{id}")
    suspend fun getMovieById(
        @Path("id") id: String
    ): MovieDto

    // POST запрос для создания нового фильма
    @POST("api/collections/movies/records")
    suspend fun createMovie(
        @Body movie: MovieRequest
    ): MovieDto

    // PATCH запрос для обновления существующего фильма
    @PATCH("api/collections/movies/records/{id}")
    suspend fun updateMovie(
        @Path("id") id: String,
        @Body movie: MovieRequest
    ): MovieDto

    // DELETE запрос для удаления фильма по ID
    @DELETE("api/collections/movies/records/{id}")
    suspend fun deleteMovie(
        @Path("id") id: String
    ): retrofit2.Response<Unit>
}