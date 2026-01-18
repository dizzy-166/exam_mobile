package com.example.exam_mobile.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.exam_mobile.presentation.navigation.Routes
import com.example.exam_mobile.presentation.viewmodel.MovieDetailsViewModel
import com.example.exam_mobile.presentation.viewmodel.MoviesListViewModel

@Composable
fun MovieDetailsScreen(
    movieId: String,
    navController: NavController,
    movieDetailsViewModel: MovieDetailsViewModel = hiltViewModel(),
    moviesListViewModel: MoviesListViewModel// shared viewModel
) {
    val state by movieDetailsViewModel.state.collectAsState()

    LaunchedEffect(movieId) {
        movieDetailsViewModel.loadMovie(movieId)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (state.error != null) {
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
        } else {
            state.movie?.let { movie ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(text = movie.title, style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Жанр: ${movie.genre}", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Страна: ${movie.country}", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Режиссер: ${movie.director}", style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Рейтинг КиноПоиск: ${movie.ratingKinoPoisk ?: "-"}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "Рейтинг IMDB: ${movie.ratingIMDB ?: "-"}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = movie.description, style = MaterialTheme.typography.bodyMedium)

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Button(onClick = { navController.navigate(Routes.EditMovie.passId(movie.id)) }) {
                            Text("Редактировать")
                        }

                        Button(
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                            onClick = {
                                movieDetailsViewModel.deleteMovie(movieId) {
                                    navController.popBackStack()
                                    moviesListViewModel.loadMovies() // 🔹 обновляем список
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