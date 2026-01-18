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
