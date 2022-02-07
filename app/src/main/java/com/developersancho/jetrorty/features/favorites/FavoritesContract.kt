package com.developersancho.jetrorty.features.favorites

import com.developersancho.model.dto.CharacterDto

data class FavoritesViewState(val list: List<CharacterDto>)

sealed class FavoritesEvent {
    object LoadFavorite : FavoritesEvent()
    data class DeleteItem(val id: Int) : FavoritesEvent()
}