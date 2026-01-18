package com.example.exam_mobile.presentation.navigation

sealed class Routes(val route: String) {
    object Login : Routes("login")
    object MoviesList : Routes("movies_list")
    object MovieDetails : Routes("movie_details/{id}") {
        fun passId(id: String) = "movie_details/$id"
    }
    object CreateMovie : Routes("create_movie")
    object EditMovie : Routes("edit_movie/{id}") {
        fun passId(id: String) = "edit_movie/$id"
    }
}