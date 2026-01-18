package com.example.exam_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.exam_mobile.presentation.navigation.RootNavGraph
import com.example.exam_mobile.ui.theme.Exam_mobileTheme
import dagger.hilt.android.AndroidEntryPoint

// Главная Activity приложения
@AndroidEntryPoint // Обязательно для Hilt
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Применение темы приложения
            Exam_mobileTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), // Заполнение всего экрана
                    color = MaterialTheme.colorScheme.background // Фоновый цвет из темы
                ) {
                    // Корневой граф навигации
                    RootNavGraph()
                }
            }
        }
    }
}