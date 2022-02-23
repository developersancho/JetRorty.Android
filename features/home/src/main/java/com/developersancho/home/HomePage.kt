/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.developersancho.characters.CharactersPage
import com.developersancho.favorites.FavoritesPage
import com.developersancho.provider.NavigationProvider
import com.developersancho.settings.SettingsPage
import com.developersancho.ui.theme.selectedBottomItemColor
import com.developersancho.ui.theme.unselectedBottomItemColor
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding
import com.ramcosta.composedestinations.annotation.Destination

@Destination(start = true)
@Composable
fun HomePage(navigator: NavigationProvider) {
    val scaffoldState = rememberScaffoldState()
    val (currentBottomTab, setCurrentBottomTab) = rememberSaveable { mutableStateOf(BottomHomeItem.CHARACTERS) }

    Crossfade(currentBottomTab) { bottomTab ->
        Scaffold(
            scaffoldState = scaffoldState,
            bottomBar = { HomeBottomNavigation(bottomTab, setCurrentBottomTab) },
            content = {
                val modifier = Modifier.padding(it)
                when (bottomTab) {
                    BottomHomeItem.CHARACTERS -> CharactersPage(
                        modifier = modifier,
                        navigator = navigator
                    )
                    BottomHomeItem.FAVORITES -> FavoritesPage(
                        modifier = modifier,
                        navigator = navigator
                    )
                    BottomHomeItem.SETTINGS -> SettingsPage(
                        modifier = modifier,
                        navigator = navigator
                    )
                }
            }
        )
    }
}

@Composable
private fun HomeBottomNavigation(
    bottomTab: BottomHomeItem,
    setCurrentBottomTab: (BottomHomeItem) -> Unit
) {
    val bottomBarHeight = 56.dp
    val pages = BottomHomeItem.values()

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsHeight(bottomBarHeight)
    ) {
        pages.forEach { page ->
            val selected = page == bottomTab
            val selectedLabelColor = if (selected) {
                selectedBottomItemColor
            } else {
                unselectedBottomItemColor
            }
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = page.icon),
                        contentDescription = stringResource(page.title)
                    )
                },
                label = {
                    Text(
                        text = stringResource(page.title),
                        color = selectedLabelColor
                    )
                },
                selected = selected,
                onClick = {
                    setCurrentBottomTab.invoke(page)
                },
                selectedContentColor = selectedBottomItemColor,
                unselectedContentColor = unselectedBottomItemColor,
                alwaysShowLabel = true,
                modifier = Modifier.navigationBarsPadding()
            )
        }
    }
}