package com.developersancho.repository.langauge

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class LanguageRepository @Inject constructor(context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "language_pref")
    private val defaultLanguage = "en"

    private object PreferencesKey {
        val languageKey = stringPreferencesKey(name = "language")
    }

    private val dataStore = context.dataStore

    suspend fun setLanguage(language: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.languageKey] = language
        }
    }

    val getLanguage: Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[PreferencesKey.languageKey] ?: defaultLanguage
        }
}