package com.developersancho.home

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.developersancho.theme.R

enum class BottomBarHomeItem(
    @StringRes val title: Int,
    val icon: ImageVector
) {
    CHARACTERS(
        title = R.string.bottom_menu_characters,
        icon = Icons.Filled.AccountCircle
    ),
    EPISODES(
        title = R.string.bottom_menu_episodes,
        icon = Icons.Filled.Dashboard
    ),
    LOCATIONS(
        title = R.string.bottom_menu_locations,
        icon = Icons.Filled.LocationCity
    ),
    SETTINGS(
        title = R.string.bottom_menu_settings,
        icon = Icons.Filled.Settings
    );
}