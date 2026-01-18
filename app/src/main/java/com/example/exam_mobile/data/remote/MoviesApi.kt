package com.example.exam_mobile.data.remote

import com.example.exam_mobile.data.dto.MovieDto
import com.example.exam_mobile.data.dto.MovieRequest
import com.example.exam_mobile.data.dto.MoviesResponseDto
import retrofit2.http.*
interface MoviesApi {

    @GET("api/collections/movies/records")
    suspend fun getMovies(): MoviesResponseDto

    @GET("api/collections/movies/records/{id}")
    suspend fun getMovieById(
        @Path("id") id: String
    ): MovieDto

    @POST("api/collections/movies/records")
    suspend fun createMovie(
        @Body movie: MovieRequest
    ): MovieDto

    @PATCH("api/collections/movies/records/{id}")
    suspend fun updateMovie(
        @Path("id") id: String,
        @Body movie: MovieRequest
    ): MovieDto

    @DELETE("api/collections/movies/records/{id}")
    suspend fun deleteMovie(
        @Path("id") id: String
    ): retrofit2.Response<Unit>
}