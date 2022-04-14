package com.developersancho.framework.pref

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.developersancho.framework.extension.fromJson
import com.developersancho.framework.extension.fromJsonList
import com.developersancho.framework.extension.toJson

/**
 * Manages Shared Preferences and provides utility functions
 * Can read and write simple values
 * @property context Required to access SharedPreferences
 * @property prefFileName Parent name of the SharedPreferences space
 */
@Suppress("UNCHECKED_CAST")
class CacheManager(
    private val context: Context,
    private var prefFileName: String? = null
) {

    private val prefs: SharedPreferences = context.getPrefs(
        prefFileName ?: context.getDefaultSharedPrefName()
    )

    /**
     * Reads a single String, Int, Boolean or Long value from SharedPreferences
     * @param T Object type to read, determined from defaultValue
     * @param key Key of the value
     * @param defaultValue Default value to return if the key does not exist
     * @return A single String, Int, Boolean or Long value
     */
    fun <T> read(key: String, defaultValue: T): T {
        return when (defaultValue) {
            is String -> prefs.getString(key, defaultValue as String) as T ?: defaultValue
            is Int -> prefs.getInt(key, defaultValue as Int) as T ?: defaultValue
            is Boolean -> prefs.getBoolean(key, defaultValue as Boolean) as T ?: defaultValue
            is Long -> prefs.getLong(key, defaultValue as Long) as T ?: defaultValue
            else -> defaultValue
        }
    }

    /**
     * Stores a single String, Int, Boolean or Long value to SharedPreferences
     * @param T Object type to write, determined from value
     * @param key Key to write to
     * @param value Object of String, Int, Boolean or Long types to store
     */
    fun <T> write(key: String, value: T) {
        when (value) {
            is String -> prefs.edit { putString(key, value).apply() }
            is Int -> prefs.edit { putInt(key, value).apply() }
            is Boolean -> prefs.edit { putBoolean(key, value).apply() }
            is Long -> prefs.edit { putLong(key, value).apply() }
            else -> Unit
        }
    }

    /**
     * Deletes an object from SharedPreferences
     * @param key to be removed
     */
    fun clear(key: String): Unit = prefs.edit {
        remove(key)
    }

    /**
     * Clears all the data under current SharedPreferences name
     * @param callBack Function to be executed after completing the operation
     */
    fun clearEverything(callBack: () -> Unit = {}) {
        prefs.edit {
            clear().commit()
            callBack.invoke()
        }
    }

    /**
     * Reads json from SharedPreferences and casts it to requested type using Moshi
     * @param T Type parameter to cast Moshi to
     * @param key Key to read from
     * @return An object of requested type
     */
    inline fun <reified T> readObject(key: String): T? {
        val readValue = read(key, "")
        return if (readValue.isEmpty()) {
            null
        } else {
            readValue.fromJson()
        }
    }

    /**
     * Stores an object under given key or class name.
     * @param key Key to write object to. If not given, class name will be used
     * @param value Object to store.
     */
    fun writeObject(key: String, value: Any) {
        write(key, value.toJson())
    }

    /**
     * Read a list object from json string and casts it to requested type using Moshi
     * @param key Key to read from
     * @return List Object
     */
    inline fun <reified T> readListObject(key: String): List<T>? {
        return try {
            val value = read(key, "")
            if (value.isEmpty()) {
                null
            } else {
                value.fromJsonList<T>()
            }
        } catch (ex: Exception) {
            null
        }
    }
}