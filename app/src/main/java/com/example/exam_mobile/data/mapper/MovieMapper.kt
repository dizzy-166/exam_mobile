package com.example.exam_mobile.data.mapper

import com.example.exam_mobile.data.dto.MovieDto
import com.example.exam_mobile.domain.model.Movie

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