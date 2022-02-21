package com.developersancho.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.developersancho.ui.resource.R

enum class BottomHomeItem(
    @StringRes val title: Int,
    @DrawableRes val icon: Int = 0
) {
    CHARACTERS(
        title = R.string.bottom_menu_characters,
        icon = R.drawable.ic_feeds
    ),
    FAVORITES(
        title = R.string.bottom_menu_favorites,
        icon = R.drawable.ic_favorite
    ),
    SETTINGS(
        title = R.string.bottom_menu_settings,
        icon = R.drawable.ic_settings
    );

    companion object {
        fun getPageFromResource(@StringRes resource: Int): BottomHomeItem {
            return when (resource) {
                R.string.bottom_menu_characters -> CHARACTERS
                R.string.bottom_menu_favorites -> FAVORITES
                R.string.bottom_menu_settings -> SETTINGS
                else -> CHARACTERS
            }
        }
    }
}