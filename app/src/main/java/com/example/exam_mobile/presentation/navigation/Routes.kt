package com.example.exam_mobile.presentation.navigation

// Класс для определения маршрутов навигации в приложении
sealed class Routes(val route: String) {
    object Splash : Routes("splash") // Экран загрузки
    object Login : Routes("login") // Экран входа
    object MoviesList : Routes("movies_list") // Список фильмов
    object MovieDetails : Routes("movie_details/{movieId}") { // Детали фильма с параметром
        fun passId(id: String) = "movie_details/$id" // Метод для построения пути с ID
    }
    object CreateMovie : Routes("create_movie") // Создание нового фильма
    object EditMovie : Routes("edit_movie/{movieId}") { // Редактирование фильма с параметром
        fun passId(id: String) = "edit_movie/$id" // Метод для построения пути с ID
    }
}