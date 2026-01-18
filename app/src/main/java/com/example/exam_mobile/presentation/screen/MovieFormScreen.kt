package com.example.exam_mobile.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.exam_mobile.presentation.viewmodel.MovieFormViewModel
import com.example.exam_mobile.presentation.viewmodel.MoviesListViewModel

@Composable
fun MovieFormScreen(
    navController: NavController,
    movieId: String? = null,
    viewModel: MovieFormViewModel = hiltViewModel(),
    moviesListViewModel: MoviesListViewModel // ✅ теперь обязательно передаём
) {
    val state by viewModel.state.collectAsState()

    // Загружаем фильм, если редактируем
    LaunchedEffect(movieId) {
        movieId?.let { viewModel.loadMovie(it) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        OutlinedTextField(
            value = state.title,
            onValueChange = { viewModel.onFieldChange("title", it) },
            label = { Text("Название фильма") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = state.genre,
            onValueChange = { viewModel.onFieldChange("genre", it) },
            label = { Text("Жанр") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = state.description,
            onValueChange = { viewModel.onFieldChange("description", it) },
            label = { Text("Описание") },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            maxLines = 5
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = state.country,
            onValueChange = { viewModel.onFieldChange("country", it) },
            label = { Text("Страна") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = state.director,
            onValueChange = { viewModel.onFieldChange("director", it) },
            label = { Text("Режиссёр") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = state.ratingKinoPoisk?.toString() ?: "",
            onValueChange = { viewModel.onFieldChange("ratingKinoPoisk", it) },
            label = { Text("Рейтинг КиноПоиск") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = state.ratingIMDB?.toString() ?: "",
            onValueChange = { viewModel.onFieldChange("ratingIMDB", it) },
            label = { Text("Рейтинг IMDB") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.submit {
                    // ⚡️ Обновляем список фильмов через shared ViewModel
                    moviesListViewModel.loadMovies()
                    navController.popBackStack()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (state.movieId == null) "Создать" else "Сохранить")
        }

        state.error?.let { errorMessage ->
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
        }
    }
}
