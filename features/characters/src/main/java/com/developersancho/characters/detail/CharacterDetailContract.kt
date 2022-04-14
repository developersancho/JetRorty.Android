package com.developersancho.characters.detail

import com.developersancho.model.dto.character.CharacterDto

data class CharacterDetailViewState(
    val character: CharacterDto? = null
)

sealed class CharacterDetailEvent {
    data class LoadDetail(val id: Int) : CharacterDetailEvent()
}