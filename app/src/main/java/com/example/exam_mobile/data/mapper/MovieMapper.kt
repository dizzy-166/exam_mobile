package com.example.exam_mobile.data.mapper

import com.example.exam_mobile.data.dto.MovieDto
import com.example.exam_mobile.domain.model.Movie

// Мапперы для преобразования между слоями данных

// Преобразование MovieDto (слой данных) в Movie (доменный слой)
fun MovieDto.toDomain(): Movie {
    return Movie(
        id = id,
        title = title.orEmpty(),
        description = description.orEmpty(),
        ratingKinoPoisk = ratingKinoPoisk,
        ratingIMDB = ratingIMDB,
        genre = genre.orEmpty(),
        country = country.orEmpty(),
        director = director.orEmpty(),
        created = created,
        updated = updated
    )
}

// Преобразование Movie (доменный слой) в Map<String, Any?> для отправки на сервер
// Используется при создании или обновлении фильма
fun Movie.toRequestMap(): Map<String, Any?> {
    return mapOf(
        "title" to title,
        "description" to description,
        "ratingKinoPoisk" to ratingKinoPoisk,
        "ratingIMDB" to ratingIMDB,
        "genre" to genre,
        "country" to country,
        "director" to director
    )
}