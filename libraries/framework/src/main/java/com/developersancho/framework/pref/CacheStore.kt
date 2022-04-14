package com.developersancho.framework.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

class CacheStore(context: Context, fileName: String) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = fileName)
    var dataStore = context.dataStore

    suspend fun <T> read(key: String, defaultValue: T): T {
        val preferences = dataStore.data.first()
        return when (defaultValue) {
            is String -> preferences[stringPreferencesKey(key)] as T ?: defaultValue
            is Int -> preferences[intPreferencesKey(key)] as T ?: defaultValue
            is Boolean -> preferences[booleanPreferencesKey(key)] as T ?: defaultValue
            is Long -> preferences[longPreferencesKey(key)] as T ?: defaultValue
            is Double -> preferences[doublePreferencesKey(key)] as T ?: defaultValue
            is Float -> preferences[floatPreferencesKey(key)] as T ?: defaultValue
            else -> defaultValue
        }
    }

    suspend fun <T> write(key: String, value: T) {
        when (value) {
            is String -> dataStore.edit { it[stringPreferencesKey(key)] = value }
            is Int -> dataStore.edit { it[intPreferencesKey(key)] = value }
            is Boolean -> dataStore.edit { it[booleanPreferencesKey(key)] = value }
            is Long -> dataStore.edit { it[longPreferencesKey(key)] = value }
            is Double -> dataStore.edit { it[doublePreferencesKey(key)] = value }
            is Float -> dataStore.edit { it[floatPreferencesKey(key)] = value }
            else -> Unit
        }
    }

    suspend fun clearAll() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}