package com.example.exam_mobile.presentation.screen.movie_form

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.exam_mobile.presentation.viewmodel.MovieFormViewModel

// Экран формы для создания/редактирования фильма
@Composable
fun MovieFormScreen(
    navController: NavController,
    onMovieSaved: () -> Unit = {}, // Коллбэк после сохранения фильма
    movieId: String? = null, // ID фильма для редактирования (null для создания нового)
    movieFormViewModel: MovieFormViewModel = hiltViewModel()
) {
    val state by movieFormViewModel.state.collectAsState()

    // Загрузка данных фильма при передаче movieId (режим редактирования)
    LaunchedEffect(movieId) {
        movieId?.let { movieFormViewModel.loadMovie(it) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Заголовок формы
        Text(
            text = if (movieId != null) "Редактирование фильма" else "Создание фильма",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Название
        OutlinedTextField(
            value = state.title,
            onValueChange = { movieFormViewModel.onFieldChange("title", it) },
            label = { Text("Название") },
            modifier = Modifier.fillMaxWidth()
        )

        // Описание
        OutlinedTextField(
            value = state.description,
            onValueChange = { movieFormViewModel.onFieldChange("description", it) },
            label = { Text("Описание") },
            modifier = Modifier.fillMaxWidth()
        )

        // Жанр
        OutlinedTextField(
            value = state.genre,
            onValueChange = { movieFormViewModel.onFieldChange("genre", it) },
            label = { Text("Жанр") },
            modifier = Modifier.fillMaxWidth()
        )

        // Страна
        OutlinedTextField(
            value = state.country,
            onValueChange = { movieFormViewModel.onFieldChange("country", it) },
            label = { Text("Страна") },
            modifier = Modifier.fillMaxWidth()
        )

        // Режиссер
        OutlinedTextField(
            value = state.director,
            onValueChange = { movieFormViewModel.onFieldChange("director", it) },
            label = { Text("Режиссёр") },
            modifier = Modifier.fillMaxWidth()
        )

        // Рейтинг КиноПоиска
        OutlinedTextField(
            value = state.ratingKinoPoisk,
            onValueChange = { movieFormViewModel.onFieldChange("ratingKinoPoisk", it) },
            label = { Text("Рейтинг Кинопоиск") },
            modifier = Modifier.fillMaxWidth()
        )

        // Рейтинг IMDb
        OutlinedTextField(
            value = state.ratingIMDB,
            onValueChange = { movieFormViewModel.onFieldChange("ratingIMDB", it) },
            label = { Text("Рейтинг IMDb") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Отображение ошибки (если есть)
        state.error?.let { error ->
            Text(
                text = "Ошибка: $error",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        // Кнопка сохранения
        Button(
            onClick = {
                movieFormViewModel.submit {
                    navController.popBackStack() // Возвращаемся назад
                    onMovieSaved() // Вызываем коллбэк для обновления списка
                }
            },
            enabled = !state.isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp))
            } else {
                Text(if (movieId != null) "Сохранить" else "Создать")
            }
        }

        // Кнопка отмены
        OutlinedButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text("Отмена")
        }
    }
}