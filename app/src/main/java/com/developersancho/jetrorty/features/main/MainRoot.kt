package com.developersancho.jetrorty.features.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.developersancho.jetrorty.features.NavGraphs
import com.developersancho.jetrorty.features.destinations.DetailScreenDestination
import com.developersancho.jetrorty.features.destinations.HomeScreenDestination
import com.developersancho.jetrorty.features.detail.DetailScreen
import com.developersancho.jetrorty.features.detail.DetailViewModel
import com.developersancho.jetrorty.features.home.HomeScreen
import com.developersancho.jetrorty.features.navDestination
import com.developersancho.jetrorty.features.startDestination
import com.developersancho.ui.theme.JetRortyTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.manualcomposablecalls.composable
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MainRoot(finish: () -> Unit) {
    JetRortyTheme {
        val navController = rememberNavController()
        val currentBackStackEntryAsState by navController.currentBackStackEntryAsState()
        val destination = currentBackStackEntryAsState?.navDestination
            ?: NavGraphs.root.startRoute.startDestination

        if (destination == NavGraphs.root.startRoute.startDestination) {
            BackHandler { finish() }
        }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            DestinationsNavHost(navGraph = NavGraphs.root, navController = navController) {
                composable(HomeScreenDestination) {
                    HomeScreen(navigator = destinationsNavigator)
                }
                composable(DetailScreenDestination) {
                    val viewModel = getViewModel<DetailViewModel>(
                        parameters = { parametersOf(navArgs.id) }
                    )
                    DetailScreen(
                        id = navArgs.id,
                        viewModel = getViewModel(),
                        navigator = destinationsNavigator
                    )
                }
            }
        }

/*        @Destination(style = DestinationStyle.BottomSheet::class)
        @Composable
        fun ColumnScope.SomeBottomSheetScreen() { *//*...*//* }*/

        /*val bottomSheetNavigator = rememberBottomSheetNavigator()
        navController.navigatorProvider += bottomSheetNavigator

        ModalBottomSheetLayout(bottomSheetNavigator = bottomSheetNavigator) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }*/
    }
}