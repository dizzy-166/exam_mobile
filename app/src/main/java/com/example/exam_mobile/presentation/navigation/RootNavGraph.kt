package com.example.exam_mobile.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.exam_mobile.presentation.screen.LoginScreen
import com.example.exam_mobile.presentation.screen.MovieDetailsScreen
import com.example.exam_mobile.presentation.screen.MovieFormScreen
import com.example.exam_mobile.presentation.screen.MoviesListScreen


@Composable
fun RootNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.route) {

        composable(Routes.Login.route) {
            LoginScreen(onSuccess = {
                navController.navigate(Routes.MoviesList.route) {
                    popUpTo(Routes.Login.route) { inclusive = true }
                }
            })
        }

        composable(Routes.MoviesList.route) {
            MoviesListScreen(navController = navController)
        }

        composable(
            Routes.MovieDetails.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("id") ?: return@composable
            MovieDetailsScreen(movieId = movieId, navController = navController)
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