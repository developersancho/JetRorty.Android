package com.developersancho.welcome

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
import com.developersancho.jetframework.SetupSystemUi
import com.developersancho.provider.LanguageProvider
import com.developersancho.theme.JetRortyColors
import com.developersancho.theme.JetRortyTheme
import com.developersancho.welcome.navgraph.WelcomeNavGraph
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WelcomeActivity : FragmentActivity() {
    @Inject
    lateinit var languageProvider: LanguageProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            languageProvider.setLocale(languageProvider.getLanguageCode(), LocalContext.current)
            WelcomeRoot()
        }
    }
}

@Composable
private fun WelcomeRoot() {
    JetRortyTheme {
        SetupSystemUi(rememberSystemUiController(), JetRortyColors.primary)
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = JetRortyColors.background
        ) {
            WelcomeNavGraph()
        }
    }
}