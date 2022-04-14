package com.developersancho.jetrorty.provider

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.developersancho.framework.pref.getPrefs
import com.developersancho.provider.ThemeProvider
import com.developersancho.theme.R
import kotlinx.coroutines.flow.*

class AppThemeProvider constructor(private val context: Context) : ThemeProvider {
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