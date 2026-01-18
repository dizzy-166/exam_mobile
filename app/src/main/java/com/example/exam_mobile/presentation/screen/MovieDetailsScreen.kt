package com.example.exam_mobile.presentation.screen

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.exam_mobile.presentation.navigation.Routes
import com.example.exam_mobile.presentation.viewmodel.MovieDetailsViewModel
import com.example.exam_mobile.ui.theme.Blue40
import com.example.exam_mobile.ui.theme.Blue80

// Экран деталей фильма
@Composable
fun MovieDetailsScreen(
    movieId: String, // ID фильма для загрузки
    navController: NavController,
    onMovieDeleted: () -> Unit = {}, // Коллбэк после удаления фильма
    movieDetailsViewModel: MovieDetailsViewModel = hiltViewModel()
) {
    // Состояние экрана
    val state by movieDetailsViewModel.state.collectAsState()
    val isDarkTheme = isSystemInDarkTheme()
    val blueColor = if (isDarkTheme) Blue80 else Blue40 // Цвет для контента в зависимости от темы

    // Загрузка фильма при изменении movieId
    LaunchedEffect(movieId) {
        movieDetailsViewModel.loadMovie(movieId)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Состояние загрузки
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        // Ошибка
        else if (state.error != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = state.error ?: "", color = MaterialTheme.colorScheme.error)
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { movieDetailsViewModel.loadMovie(movieId) }) {
                    Text("Повторить")
                }
            }
        }
        // Успешная загрузка
        else {
            state.movie?.let { movie ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    // Заголовок (фиксированный цвет темы)
                    Text(
                        text = movie.title ?: "Без названия",
                        style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Описание фильма
                    Text(
                        text = "Описание:",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = blueColor // Синий цвет в зависимости от темы
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = movie.description ?: "Описание отсутствует",
                        style = MaterialTheme.typography.bodyLarge.copy(color = blueColor),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Рейтинги (рядом)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        // Рейтинг КиноПоиска
                        Column {
                            Text(
                                text = "Кинопоиск",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontWeight = FontWeight.Medium,
                                    color = blueColor
                                )
                            )
                            Text(
                                text = movie.ratingKinoPoisk?.let {
                                    String.format("%.1f из 10", it)
                                } ?: "-",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = blueColor
                                )
                            )
                        }

                        // Рейтинг IMDb
                        Column {
                            Text(
                                text = "IMDb",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontWeight = FontWeight.Medium,
                                    color = blueColor
                                )
                            )
                            Text(
                                text = movie.ratingIMDB?.let {
                                    String.format("%.1f из 10", it)
                                } ?: "-",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = blueColor
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Жанр
                    Text(
                        text = "Жанр:",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = blueColor
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = movie.genre ?: "Не указан",
                        style = MaterialTheme.typography.bodyLarge.copy(color = blueColor)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Страна
                    Text(
                        text = "Страны:",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = blueColor
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = movie.country ?: "Не указаны",
                        style = MaterialTheme.typography.bodyLarge.copy(color = blueColor)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Режиссер
                    Text(
                        text = "Режиссёр:",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = blueColor
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = movie.director ?: "Не указан",
                        style = MaterialTheme.typography.bodyLarge.copy(color = blueColor)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    // Кнопки действий
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Кнопка редактирования
                        Button(
                            onClick = { navController.navigate(Routes.EditMovie.passId(movie.id)) },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Редактировать")
                        }

                        // Кнопка удаления (красная)
                        Button(
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.error
                            ),
                            onClick = {
                                movieDetailsViewModel.deleteMovie(movieId) {
                                    navController.popBackStack() // Возврат назад
                                    onMovieDeleted() // Обновление списка
                                }
                            }
                        ) {
                            Text("Удалить")
                        }
                    }
                }
            }
        }
    }
}