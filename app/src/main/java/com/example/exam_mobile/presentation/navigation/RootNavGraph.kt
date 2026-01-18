package com.example.exam_mobile.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.exam_mobile.presentation.screen.LoginScreen
import com.example.exam_mobile.presentation.screen.MovieDetailsScreen
import com.example.exam_mobile.presentation.screen.MoviesListScreen
import com.example.exam_mobile.presentation.screen.movie_form.MovieFormScreen
import com.example.exam_mobile.presentation.viewmodel.MoviesListViewModel

@Composable
fun RootNavGraph() {
    val navController = rememberNavController()

    // Shared ViewModel для списка фильмов
    val moviesListViewModel = hiltViewModel<MoviesListViewModel>()

    NavHost(navController = navController, startDestination = Routes.Login.route) {

        // Login
        composable(Routes.Login.route) {
            LoginScreen(onSuccess = {
                navController.navigate(Routes.MoviesList.route) {
                    popUpTo(Routes.Login.route) { inclusive = true }
                }
            })
        }

        // Список фильмов
        composable(Routes.MoviesList.route) {
            MoviesListScreen(
                navController = navController,
                viewModel = moviesListViewModel
            )
        }

        // Детали фильма
        composable(
            Routes.MovieDetails.route,
            arguments = listOf(navArgument("movieId") { type = NavType.StringType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId") ?: ""
            MovieDetailsScreen(
                navController = navController,
                movieId = movieId,
                onMovieDeleted = {
                    // Обновляем список после удаления
                    moviesListViewModel.loadMovies()
                }
            )
        }

        // Создание нового фильма
        composable(Routes.CreateMovie.route) {
            MovieFormScreen(
                navController = navController,
                onMovieSaved = {
                    // Обновляем список после создания
                    moviesListViewModel.loadMovies()
                }
            )
        }

        // Редактирование фильма
        composable(
            Routes.EditMovie.route,
            arguments = listOf(navArgument("movieId") { type = NavType.StringType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId") ?: ""
            MovieFormScreen(
                navController = navController,
                movieId = movieId,
                onMovieSaved = {
                    // Обновляем список после редактирования
                    moviesListViewModel.loadMovies()
                }
            )
        }
    }
}