package com.example.exam_mobile.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.exam_mobile.presentation.navigation.Routes
import com.example.exam_mobile.presentation.viewmodel.MoviesListViewModel
import com.example.exam_mobile.ui.theme.Blue40
import com.example.exam_mobile.ui.theme.Blue80

// Главный экран со списком фильмов
@Composable
fun MoviesListScreen(
    navController: NavController,
    viewModel: MoviesListViewModel
) {
    val state by viewModel.state.collectAsState()
    var searchQuery by remember { mutableStateOf("") } // Поисковый запрос
    val isDarkTheme = isSystemInDarkTheme()
    val blueColor = if (isDarkTheme) Blue80 else Blue40 // Цвет в зависимости от темы

    Column(modifier = Modifier.fillMaxSize()) {

        // Заголовок экрана
        Text(
            text = "Фильмы/Главная",
            style = MaterialTheme.typography.headlineMedium.copy(color = blueColor),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        // Подзаголовок
        Text(
            text = "Каталог фильмов:",
            style = MaterialTheme.typography.titleLarge.copy(color = blueColor),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Поле поиска
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Поиск фильма", color = blueColor) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = blueColor,
                unfocusedTextColor = blueColor,
                focusedBorderColor = blueColor,
                unfocusedBorderColor = blueColor.copy(alpha = 0.5f),
                focusedLabelColor = blueColor,
                unfocusedLabelColor = blueColor.copy(alpha = 0.7f),
                cursorColor = blueColor
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Основной контент
        Box(modifier = Modifier.weight(1f)) {
            when {
                // Состояние загрузки
                state.isLoading -> {
                    CircularProgressIndicator(
                        color = blueColor,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                // Ошибка
                state.error != null -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = state.error ?: "",
                            color = blueColor
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = { viewModel.loadMovies() }) {
                            Text("Повторить")
                        }
                    }
                }

                // Успешная загрузка
                else -> {
                    // Фильтрация фильмов по поисковому запросу
                    val filteredMovies = if (searchQuery.isEmpty()) {
                        state.movies
                    } else {
                        state.movies.filter {
                            it.title.contains(searchQuery, ignoreCase = true)
                        }
                    }

                    // Список фильмов
                    LazyColumn(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(0.dp)
                    ) {
                        itemsIndexed(filteredMovies) { index, movie ->
                            Column {
                                // Элемент фильма
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            // Переход к деталям фильма
                                            navController.navigate(
                                                Routes.MovieDetails.passId(movie.id)
                                            )
                                        }
                                        .padding(vertical = 12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    // Название фильма
                                    Text(
                                        text = movie.title,
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = blueColor
                                        ),
                                        modifier = Modifier.weight(1f)
                                    )

                                    // Рейтинг IMDb
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = "⭐",
                                            fontSize = 16.sp,
                                            color = blueColor
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = String.format("%.1f", movie.ratingIMDB),
                                            style = MaterialTheme.typography.bodyMedium.copy(
                                                color = blueColor
                                            )
                                        )
                                    }
                                }

                                // Разделитель (кроме последнего элемента)
                                if (index < filteredMovies.lastIndex) {
                                    Divider(
                                        thickness = 1.dp,
                                        color = blueColor.copy(alpha = 0.3f)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Кнопка создания нового фильма
        Button(
            onClick = { navController.navigate(Routes.CreateMovie.route) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Создать фильм")
        }
    }
}