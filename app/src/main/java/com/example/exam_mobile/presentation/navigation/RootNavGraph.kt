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
import com.example.exam_mobile.presentation.screen.MovieFormScreen
import com.example.exam_mobile.presentation.screen.MoviesListScreen
import com.example.exam_mobile.presentation.viewmodel.MoviesListViewModel


@Composable
fun RootNavGraph() {
    val navController = rememberNavController()

    // Shared MoviesListViewModel для списка фильмов
    val moviesListViewModel: MoviesListViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = Routes.Login.route) {

        composable(Routes.Login.route) {
            LoginScreen(onSuccess = {
                navController.navigate(Routes.MoviesList.route) {
                    popUpTo(Routes.Login.route) { inclusive = true }
                }
            })
        }

        composable(Routes.MoviesList.route) {
            MoviesListScreen(
                navController = navController,
                viewModel = moviesListViewModel // передаем shared
            )
        }

        composable(
            Routes.MovieDetails.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("id") ?: return@composable
            MovieDetailsScreen(
                movieId = movieId,
                navController = navController,
                moviesListViewModel = moviesListViewModel // передаем shared
            )
        }

        composable(Routes.CreateMovie.route) {
            MovieFormScreen(navController = navController)
        }

        composable(
            Routes.EditMovie.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("id") ?: return@composable
            MovieFormScreen(navController = navController, movieId = movieId)
        }
    }
}
