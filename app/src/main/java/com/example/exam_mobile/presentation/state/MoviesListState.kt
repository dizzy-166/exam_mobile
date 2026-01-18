package com.example.exam_mobile.presentation.state

import com.example.exam_mobile.domain.model.Movie

data class MoviesListState(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)