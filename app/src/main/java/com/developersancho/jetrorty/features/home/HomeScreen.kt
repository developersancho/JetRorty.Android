package com.developersancho.jetrorty.features.home
//
//import androidx.compose.animation.Crossfade
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.unit.dp
//import com.developersancho.jetrorty.features.characters.CharactersScreen
//import com.developersancho.jetrorty.features.favorites.FavoritesScreen
//import com.developersancho.jetrorty.features.settings.SettingsScreen
//import com.developersancho.ui.theme.selectedBottomItemColor
//import com.developersancho.ui.theme.unselectedBottomItemColor
//import com.google.accompanist.insets.navigationBarsHeight
//import com.google.accompanist.insets.navigationBarsPadding
//import com.ramcosta.composedestinations.annotation.Destination
//import com.ramcosta.composedestinations.navigation.DestinationsNavigator
//
//@Destination(start = true)
//@Composable
//fun HomeScreen(navigator: DestinationsNavigator) {
//    val scaffoldState = rememberScaffoldState()
//    val (currentBottomTab, setCurrentBottomTab) = rememberSaveable { mutableStateOf(HomePage.CHARACTERS) }
//
//    Crossfade(currentBottomTab) { bottomTab ->
//        Scaffold(
//            scaffoldState = scaffoldState,
//            bottomBar = { HomeBottomNavigation(bottomTab, setCurrentBottomTab) },
//            content = {
//                val modifier = Modifier.padding(it)
//                when (bottomTab) {
//                    HomePage.CHARACTERS -> CharactersScreen(
//                        modifier = modifier,
//                        navigator = navigator
//                    )
//                    HomePage.FAVORITES -> FavoritesScreen(
//                        modifier = modifier,
//                        navigator = navigator
//                    )
//                    HomePage.SETTINGS -> SettingsScreen(
//                        modifier = modifier,
//                        navigator = navigator
//                    )
//                }
//            }
//        )
//    }
//}
//
//@Composable
//private fun HomeBottomNavigation(bottomTab: HomePage, setCurrentBottomTab: (HomePage) -> Unit) {
//    val bottomBarHeight = 56.dp
//    val pages = HomePage.values()
//
//    BottomNavigation(
//        backgroundColor = MaterialTheme.colors.primary,
//        modifier = Modifier
//            .fillMaxWidth()
//            .navigationBarsHeight(bottomBarHeight)
//    ) {
//        pages.forEach { page ->
//            val selected = page == bottomTab
//            val selectedLabelColor = if (selected) {
//                selectedBottomItemColor
//            } else {
//                unselectedBottomItemColor
//            }
//            BottomNavigationItem(
//                icon = {
//                    Icon(
//                        painter = painterResource(id = page.icon),
//                        contentDescription = stringResource(page.title)
//                    )
//                },
//                label = {
//                    Text(
//                        text = stringResource(page.title),
//                        color = selectedLabelColor
//                    )
//                },
//                selected = selected,
//                onClick = {
//                    setCurrentBottomTab.invoke(page)
//                },
//                selectedContentColor = selectedBottomItemColor,
//                unselectedContentColor = unselectedBottomItemColor,
//                alwaysShowLabel = true,
//                modifier = Modifier.navigationBarsPadding()
//            )
//        }
//    }
//}
