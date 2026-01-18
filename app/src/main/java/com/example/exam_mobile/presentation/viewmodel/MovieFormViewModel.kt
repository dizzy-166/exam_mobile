package com.example.exam_mobile.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exam_mobile.domain.model.Movie
import com.example.exam_mobile.domain.usecase.CreateMovieUseCase
import com.example.exam_mobile.domain.usecase.GetMovieByIdUseCase
import com.example.exam_mobile.domain.usecase.UpdateMovieUseCase
import com.example.exam_mobile.presentation.state.MovieFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieFormViewModel @Inject constructor(
    private val createMovieUseCase: CreateMovieUseCase,
    private val updateMovieUseCase: UpdateMovieUseCase,
    private val getMovieByIdUseCase: GetMovieByIdUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MovieFormState())
    val state: StateFlow<MovieFormState> = _state

    fun loadMovie(id: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val movie = getMovieByIdUseCase(id)
                _state.value = _state.value.copy(
                    title = movie.title,
                    description = movie.description,
                    ratingKinoPoisk = movie.ratingKinoPoisk?.toString() ?: "",
                    ratingIMDB = movie.ratingIMDB?.toString() ?: "",
                    genre = movie.genre,
                    country = movie.country,
                    director = movie.director,
                    movieId = movie.id,
                    isLoading = false
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun onFieldChange(field: String, value: String) {
        _state.value = when (field) {
            "title" -> _state.value.copy(title = value)
            "description" -> _state.value.copy(description = value)
            "ratingKinoPoisk" -> _state.value.copy(ratingKinoPoisk = value)
            "ratingIMDB" -> _state.value.copy(ratingIMDB = value)
            "genre" -> _state.value.copy(genre = value)
            "country" -> _state.value.copy(country = value)
            "director" -> _state.value.copy(director = value)
            else -> _state.value
        }
    }

    fun submit(onSuccess: () -> Unit) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                val movieMap = _state.value.toMap()
                if (_state.value.movieId == null) {
                    createMovieUseCase(
                        Movie(
                            id = "",
                            title = movieMap["title"] as String,
                            description = movieMap["description"] as String,
                            ratingKinoPoisk = movieMap["ratingKinoPoisk"] as? Double,
                            ratingIMDB = movieMap["ratingIMDB"] as? Double,
                            genre = movieMap["genre"] as String,
                            country = movieMap["country"] as String,
                            director = movieMap["director"] as String,
                            created = "",
                            updated = ""
                        )
                    )
                } else {
                    updateMovieUseCase(
                        _state.value.movieId!!,
                        Movie(
                            id = _state.value.movieId!!,
                            title = movieMap["title"] as String,
                            description = movieMap["description"] as String,
                            ratingKinoPoisk = movieMap["ratingKinoPoisk"] as? Double,
                            ratingIMDB = movieMap["ratingIMDB"] as? Double,
                            genre = movieMap["genre"] as String,
                            country = movieMap["country"] as String,
                            director = movieMap["director"] as String,
                            created = "",
                            updated = ""
                        )
                    )
                }
                _state.value = _state.value.copy(isLoading = false, success = true)
                onSuccess()
            } catch (e: Exception) {
                _state.value = _state.value.copy(isLoading = false, error = e.message)
            }
        }
    }
}