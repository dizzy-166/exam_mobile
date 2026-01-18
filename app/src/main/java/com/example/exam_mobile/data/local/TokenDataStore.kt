package com.example.exam_mobile.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

// DataStore для хранения токена аутентификации
// preferencesDataStore создает экземпляр DataStore для хранения пар ключ-значение
private val Context.dataStore by preferencesDataStore(name = "auth_prefs")

class TokenDataStore(
    private val context: Context
) {
    // Ключ для хранения JWT токена
    private val TOKEN_KEY = stringPreferencesKey("jwt_token")

    // Сохранить токен в DataStore
    suspend fun saveToken(token: String) {
        context.dataStore.edit { prefs ->
            prefs[TOKEN_KEY] = token
        }
    }

    // Получить токен из DataStore (возвращает null если токен не сохранен)
    suspend fun getToken(): String? {
        return context.dataStore.data
            .map { prefs -> prefs[TOKEN_KEY] }
            .first()
    }

    // Удалить токен из DataStore (для выхода из аккаунта)
    suspend fun clearToken() {
        context.dataStore.edit { prefs ->
            prefs.remove(TOKEN_KEY)
        }
    }
}