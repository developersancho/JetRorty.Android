package com.developersancho.jetrorty.features.characters

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.developersancho.domain.usecase.character.GetCharacters
import com.developersancho.domain.usecase.favorite.UpdateFavorite
import com.developersancho.framework.base.BaseViewState
import com.developersancho.framework.base.mvi.MviViewModel
import com.developersancho.model.dto.CharacterDto

class CharactersViewModel(
    private val getCharacters: GetCharacters,
    private val updateFavorite: UpdateFavorite
) : MviViewModel<BaseViewState<CharactersViewState>, CharactersEvent>() {

    private val config = PagingConfig(pageSize = 20)

    override fun onTriggerEvent(eventType: CharactersEvent) {
        when (eventType) {
            is CharactersEvent.LoadCharacters -> loadCharacters()
            is CharactersEvent.UpdateFavorite -> updateFavorite(eventType.characterDto)
        }
    }

    private fun loadCharacters() = safeLaunch {
        setState(BaseViewState.Loading)
        val params = GetCharacters.Params(config, hashMapOf())
        val pagedFlow = getCharacters(params).cachedIn(scope = viewModelScope)
        setState(BaseViewState.Data(CharactersViewState(pagedFlow)))
    }

    private fun updateFavorite(dto: CharacterDto) = safeLaunch {
        val params = UpdateFavorite.Params(dto)
        call(updateFavorite(params))
    }
}