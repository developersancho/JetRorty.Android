package com.developersancho.settings

import androidx.lifecycle.ViewModel
import com.developersancho.provider.ThemeProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val themeProvider: ThemeProvider
) : ViewModel() {
    fun isNightMode() = themeProvider.isNightMode()

    fun saveThemeMode(isChecked: Boolean) {
        themeProvider.theme = if (isChecked) {
            ThemeProvider.Theme.DARK
        } else {
            ThemeProvider.Theme.LIGHT
        }
    }
}