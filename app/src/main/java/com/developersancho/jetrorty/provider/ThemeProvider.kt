package com.developersancho.jetrorty.provider

import kotlinx.coroutines.flow.Flow

interface ThemeProvider {
    var theme: Theme
    fun observeTheme(): Flow<Theme>

    enum class Theme {
        LIGHT,
        DARK,
        SYSTEM
    }

    fun isNightMode(): Boolean

    fun setNightMode(forceNight: Boolean)
}