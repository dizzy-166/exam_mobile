package com.example.exam_mobile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exam_mobile.domain.usecase.DeleteMovieUseCase
import com.example.exam_mobile.domain.usecase.GetMoviesUseCase
import com.example.exam_mobile.presentation.state.MoviesListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val deleteMovieUseCase: DeleteMovieUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MoviesListState(isLoading = true))
    val state: StateFlow<MoviesListState> = _state

    init {
        loadMovies()
    }

    fun loadMovies() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                val movies = getMoviesUseCase()
                _state.value = _state.value.copy(
                    movies = movies,
                    isLoading = false
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message ?: "Ошибка загрузки фильмов"
                )
            }
        }
    }

    fun deleteMovie(id: String) {
        viewModelScope.launch {
            try {
                deleteMovieUseCase(id)
                loadMovies() // Перезагрузка списка после удаления
            } catch (e: Exception) {
                _state.value = _state.value.copy(error = e.message ?: "Ошибка при удалении")
            }
        }
    }
}
