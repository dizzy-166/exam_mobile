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
import com.example.exam_mobile.presentation.screen.SplashScreen
import com.example.exam_mobile.presentation.screen.movie_form.MovieFormScreen
import com.example.exam_mobile.presentation.viewmodel.MoviesListViewModel

// Корневой граф навигации приложения
@Composable
fun RootNavGraph() {
    val navController = rememberNavController()

    // Shared ViewModel для списка фильмов (разделяется между несколькими экранами)
    val moviesListViewModel = hiltViewModel<MoviesListViewModel>()

    // Начальный экран - SplashScreen
    NavHost(navController = navController, startDestination = Routes.Splash.route) {

        // Splash экран (загрузочный экран)
        composable(Routes.Splash.route) {
            SplashScreen(navController = navController)
        }

        // Экран входа
        composable(Routes.Login.route) {
            LoginScreen(onSuccess = {
                // После успешного входа переходим к списку фильмов
                navController.navigate(Routes.MoviesList.route) {
                    // Удаляем Splash и Login из стека навигации
                    popUpTo(Routes.Splash.route) { inclusive = true }
                }
            })
        }

        // Список фильмов (главный экран)
        composable(Routes.MoviesList.route) {
            MoviesListScreen(
                navController = navController,
                viewModel = moviesListViewModel
            )
        }

        // Детали фильма (с аргументом movieId)
        composable(
            Routes.MovieDetails.route,
            arguments = listOf(navArgument("movieId") { type = NavType.StringType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId") ?: ""
            MovieDetailsScreen(
                navController = navController,
                movieId = movieId,
                onMovieDeleted = {
                    // Обновляем список фильмов после удаления
                    moviesListViewModel.loadMovies()
                }
            )
        }

        // Создание нового фильма
        composable(Routes.CreateMovie.route) {
            MovieFormScreen(
                navController = navController,
                onMovieSaved = {
                    // Обновляем список фильмов после создания
                    moviesListViewModel.loadMovies()
                }
            )
        }

        // Редактирование фильма (с аргументом movieId)
        composable(
            Routes.EditMovie.route,
            arguments = listOf(navArgument("movieId") { type = NavType.StringType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId") ?: ""
            MovieFormScreen(
                navController = navController,
                movieId = movieId,
                onMovieSaved = {
                    // Обновляем список фильмов после редактирования
                    moviesListViewModel.loadMovies()
                }
            )
        }
    }
}