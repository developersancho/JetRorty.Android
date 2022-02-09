package com.developersancho.jetrorty.features.settings

import androidx.lifecycle.ViewModel
import com.developersancho.jetrorty.provider.ThemeProvider

class SettingsViewModel(private val themeProvider: ThemeProvider) : ViewModel() {
    fun isNightMode() = themeProvider.isNightMode()

    fun saveThemeMode(isChecked: Boolean) {
        themeProvider.theme = if (isChecked) {
            ThemeProvider.Theme.DARK
        } else {
            ThemeProvider.Theme.LIGHT
        }
    }
}