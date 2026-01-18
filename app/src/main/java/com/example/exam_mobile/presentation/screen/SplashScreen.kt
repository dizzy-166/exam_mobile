package com.example.exam_mobile.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import com.example.exam_mobile.R
import com.example.exam_mobile.presentation.navigation.Routes

// Экран загрузки (Splash Screen)
@Composable
fun SplashScreen(
    navController: NavController
) {
    // Автоматический переход через 2 секунды
    LaunchedEffect(key1 = true) {
        delay(2000)
        navController.navigate(Routes.Login.route) {
            // Удаляем Splash Screen из стека навигации
            popUpTo(Routes.Splash.route) { inclusive = true }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Отображение изображения splash screen
        Image(
            painter = painterResource(id = R.drawable.splash_image),
            contentDescription = "Splash Screen",
            modifier = Modifier
                .size(300.dp), // Фиксированный размер изображения
            contentScale = ContentScale.Fit // Сохраняет пропорции изображения
        )
    }
}