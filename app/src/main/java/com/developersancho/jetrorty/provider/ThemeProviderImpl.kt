package com.developersancho.jetrorty.provider

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.developersancho.framework.pref.getPrefs
import kotlinx.coroutines.flow.*
import com.developersancho.ui.resource.R
import androidx.core.content.edit

class ThemeProviderImpl constructor(
    private val context: Context
) : ThemeProvider {

    private val sharedPreferences = context.getPrefs()

    private val defaultThemeValue = context.getString(R.string.pref_theme_default_value)

    private val preferenceKeyChangedFlow = MutableSharedFlow<String>(extraBufferCapacity = 1)

    private val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        preferenceKeyChangedFlow.tryEmit(key)
    }

    companion object {
        const val KEY_THEME = "pref_theme"
    }

    init {
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
    }

    override var theme: ThemeProvider.Theme
        get() = getThemeForStorageValue(sharedPreferences.getString(KEY_THEME, defaultThemeValue)!!)
        set(value) = sharedPreferences.edit {
            putString(KEY_THEME, value.storageKey)
        }

    override fun observeTheme(): Flow<ThemeProvider.Theme> {
        return preferenceKeyChangedFlow
            // Emit on start so that we always send the initial value
            .onStart { emit(KEY_THEME) }
            .filter { it == KEY_THEME }
            .map { theme }
            .distinctUntilChanged()
    }

    override fun isNightMode(): Boolean {
        return theme == ThemeProvider.Theme.DARK
    }


    private val ThemeProvider.Theme.storageKey: String
        get() = when (this) {
            ThemeProvider.Theme.LIGHT -> context.getString(R.string.pref_theme_light_value)
            ThemeProvider.Theme.DARK -> context.getString(R.string.pref_theme_dark_value)
            ThemeProvider.Theme.SYSTEM -> context.getString(R.string.pref_theme_system_value)
        }

    private fun getThemeForStorageValue(value: String) = when (value) {
        context.getString(R.string.pref_theme_light_value) -> ThemeProvider.Theme.LIGHT
        context.getString(R.string.pref_theme_dark_value) -> ThemeProvider.Theme.DARK
        else -> ThemeProvider.Theme.SYSTEM
    }

    override fun setNightMode(forceNight: Boolean) {
        theme = if (forceNight) {
            ThemeProvider.Theme.DARK
        } else {
            ThemeProvider.Theme.LIGHT
        }
    }
}

@Composable
fun ThemeProvider.shouldUseDarkMode(): Boolean {
    val themePreference = observeTheme().collectAsState(initial = ThemeProvider.Theme.SYSTEM)
    val mode = when (themePreference.value) {
        ThemeProvider.Theme.LIGHT -> false
        ThemeProvider.Theme.DARK -> true
        else -> isSystemInDarkTheme()
    }
    setNightMode(mode)
    return mode
}