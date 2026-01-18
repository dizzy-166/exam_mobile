package com.example.exam_mobile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exam_mobile.domain.usecase.DeleteMovieUseCase
import com.example.exam_mobile.domain.usecase.GetMovieByIdUseCase
import com.example.exam_mobile.presentation.state.MovieDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// ViewModel для экрана деталей фильма
@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieByIdUseCase: GetMovieByIdUseCase,
    private val deleteMovieUseCase: DeleteMovieUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MovieDetailsState(isLoading = true))
    val state: StateFlow<MovieDetailsState> = _state

    // Загрузка фильма по ID
    fun loadMovie(id: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                val movie = getMovieByIdUseCase(id)
                _state.value = _state.value.copy(movie = movie, isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message ?: "Ошибка загрузки фильма"
                )
            }
        }
    }

    // Удаление фильма
    fun deleteMovie(id: String, onDeleted: () -> Unit) {
        viewModelScope.launch {
            try {
                deleteMovieUseCase(id)
                _state.value = _state.value.copy(deleteSuccess = true)
                onDeleted() // Коллбэк после успешного удаления
            } catch (e: Exception) {
                _state.value = _state.value.copy(error = e.message)
            }
        }
    }
}