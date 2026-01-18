package com.example.exam_mobile.presentation.state

import com.example.exam_mobile.domain.model.Movie

data class MovieDetailsState(
    val movie: Movie? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val deleteSuccess: Boolean = false
)