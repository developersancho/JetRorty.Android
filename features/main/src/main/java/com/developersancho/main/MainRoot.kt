package com.developersancho.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.developersancho.provider.ThemeProvider
import com.developersancho.provider.shouldUseDarkMode
import com.developersancho.ui.theme.JetRortyTheme
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency
import org.koin.androidx.compose.get

@Composable
fun MainRoot(finish: () -> Unit) {
    val navController = rememberNavController()

    val themeProvider: ThemeProvider = get()
    val isDarkMode = themeProvider.shouldUseDarkMode()

    val currentBackStackEntryAsState by navController.currentBackStackEntryAsState()
    val destination = currentBackStackEntryAsState?.destination?.route
        ?: RootNavGraph.startRoute.startRoute.route

    if (destination == RootNavGraph.startRoute.startRoute.route) {
        BackHandler { finish() }
    }

    JetRortyTheme(darkTheme = isDarkMode) {
        SetupSystemUi(rememberSystemUiController(), MaterialTheme.colors.primary)

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            DestinationsNavHost(
                navController = navController,
                navGraph = RootNavGraph,
                dependenciesContainerBuilder = {
                    dependency(CommonNavigationProvider(navController))
                }
            )
        }
    }

}

@Composable
internal fun SetupSystemUi(
    systemUiController: SystemUiController,
    systemColor: Color
) {
    SideEffect {
        systemUiController.setSystemBarsColor(color = systemColor)
    }
}