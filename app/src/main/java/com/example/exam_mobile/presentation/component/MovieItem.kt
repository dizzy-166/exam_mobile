package com.example.exam_mobile.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.exam_mobile.domain.model.Movie

// Composable компонент для отображения элемента фильма в списке
@Composable
fun MovieItem(
    movie: Movie,
    onClick: (String) -> Unit // Обработчик клика по фильму (передает ID фильма)
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick(movie.id) }, // Делаем всю карточку кликабельной
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Название фильма
            Text(text = movie.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))

            // Жанр фильма
            Text(
                text = movie.genre,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(2.dp))

            // Рейтинг КиноПоиска (если есть)
            Text(
                text = "Рейтинг Кинопоиск: ${movie.ratingKinoPoisk ?: "-"}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}