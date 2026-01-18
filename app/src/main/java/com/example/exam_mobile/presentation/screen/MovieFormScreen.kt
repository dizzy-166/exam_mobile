package com.example.exam_mobile.presentation.screen

import androidx.compose.foundation.layout.*
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
    movieId: String? = null,
    viewModel: MovieFormViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        // Поле Название фильма
        OutlinedTextField(
            value = state.title,
            onValueChange = { viewModel.onFieldChange("title", it) },
            label = { Text("Название фильма") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Поле Жанр
        OutlinedTextField(
            value = state.genre,
            onValueChange = { viewModel.onFieldChange("genre", it) },
            label = { Text("Жанр") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка Создать/Сохранить
        Button(
            onClick = { viewModel.submit { navController.popBackStack() } },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (movieId == null) "Создать" else "Сохранить")
        }

        // Ошибка
        state.error?.let { errorMessage ->
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
        }
    }
}
