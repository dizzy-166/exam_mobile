package com.example.exam_mobile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exam_mobile.domain.usecase.LoginUseCase
import com.example.exam_mobile.presentation.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// ViewModel для экрана входа
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state

    // Обновление email
    fun onEmailChange(email: String) {
        _state.value = _state.value.copy(email = email)
    }

    // Обновление пароля
    fun onPasswordChange(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    // Авторизация пользователя
    fun login(onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                _state.value = _state.value.copy(isLoading = true) // Начало загрузки
                loginUseCase(
                    email = _state.value.email,
                    password = _state.value.password
                )
                _state.value = _state.value.copy(isLoading = false) // Конец загрузки
                onSuccess() // Успешный коллбэк
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message // Сохранение ошибки
                )
            }
        }
    }
}