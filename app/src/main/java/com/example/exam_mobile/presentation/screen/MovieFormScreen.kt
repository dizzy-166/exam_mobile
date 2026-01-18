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

@Composable
fun MovieFormScreen(
    navController: NavController,
    onMovieSaved: () -> Unit = {}, // Callback для обновления списка
    movieId: String? = null,
    movieFormViewModel: MovieFormViewModel = hiltViewModel()
) {
    val state by movieFormViewModel.state.collectAsState()

    // Загружаем данные фильма, если передан movieId
    LaunchedEffect(movieId) {
        movieId?.let { movieFormViewModel.loadMovie(it) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = if (movieId != null) "Редактирование фильма" else "Создание фильма",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = state.title,
            onValueChange = { movieFormViewModel.onFieldChange("title", it) },
            label = { Text("Название") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = state.description,
            onValueChange = { movieFormViewModel.onFieldChange("description", it) },
            label = { Text("Описание") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = state.genre,
            onValueChange = { movieFormViewModel.onFieldChange("genre", it) },
            label = { Text("Жанр") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = state.country,
            onValueChange = { movieFormViewModel.onFieldChange("country", it) },
            label = { Text("Страна") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = state.director,
            onValueChange = { movieFormViewModel.onFieldChange("director", it) },
            label = { Text("Режиссёр") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = state.ratingKinoPoisk,
            onValueChange = { movieFormViewModel.onFieldChange("ratingKinoPoisk", it) },
            label = { Text("Рейтинг Кинопоиск") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = state.ratingIMDB,
            onValueChange = { movieFormViewModel.onFieldChange("ratingIMDB", it) },
            label = { Text("Рейтинг IMDb") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Показываем ошибку, если есть
        state.error?.let { error ->
            Text(
                text = "Ошибка: $error",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        Button(
            onClick = {
                movieFormViewModel.submit {
                    navController.popBackStack()
                    onMovieSaved() // Вызываем callback для обновления списка
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