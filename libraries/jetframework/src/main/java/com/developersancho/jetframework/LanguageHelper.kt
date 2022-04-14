package com.developersancho.jetframework

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import java.util.*

@Suppress("DEPRECATION")
@Composable
fun SetLanguage(languageCode: String) {
    val locale = Locale(languageCode)
    val configuration = LocalConfiguration.current
    configuration.setLocale(locale)
    val resources = LocalContext.current.resources
    resources.updateConfiguration(configuration, resources.displayMetrics)
}