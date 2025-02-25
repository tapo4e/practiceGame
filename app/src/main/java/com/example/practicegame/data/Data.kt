package com.example.practicegame.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class Data(private val context: Context) {
    companion object {
        val SCORE = intPreferencesKey("score")
    }
    suspend fun saveScore(score: Int) {
        context.dataStore.edit { preferences ->
            preferences[SCORE] = score
        }
    }

    val getScore: Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[SCORE] ?: 0
        }
}