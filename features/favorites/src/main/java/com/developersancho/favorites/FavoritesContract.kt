/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.favorites

import com.developersancho.model.dto.CharacterDto

data class FavoritesViewState(val list: List<CharacterDto>)

sealed class FavoritesEvent {
    object LoadFavorite : FavoritesEvent()
    data class DeleteItem(val id: Int) : FavoritesEvent()
}