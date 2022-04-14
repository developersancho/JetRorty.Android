package com.developersancho.characters.list

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.developersancho.domain.usecase.character.GetCharacters
import com.developersancho.domain.usecase.character.favorite.DeleteCharacterFavorite
import com.developersancho.domain.usecase.character.favorite.GetCharacterFavorites
import com.developersancho.domain.usecase.character.favorite.UpdateCharacterFavorite
import com.developersancho.framework.base.mvi.BaseViewState
import com.developersancho.framework.base.mvi.MviViewModel
import com.developersancho.model.dto.character.CharacterDto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharacters: GetCharacters,
    private val updateCharacterFavorite: UpdateCharacterFavorite,
    private val getFavorites: GetCharacterFavorites,
    private val deleteFavorite: DeleteCharacterFavorite
) : MviViewModel<BaseViewState<CharactersViewState>, CharactersEvent>() {

    private val config = PagingConfig(pageSize = 20)

    override fun onTriggerEvent(eventType: CharactersEvent) {
        when (eventType) {
            is CharactersEvent.LoadCharacters -> onLoadCharacters()
            is CharactersEvent.AddOrRemoveFavorite -> onAddOrRemoveFavorite(eventType.characterDto)
            is CharactersEvent.DeleteFavorite -> onDeleteFavorite(eventType.id)
            is CharactersEvent.LoadFavorites -> onLoadFavorites()
        }
    }

    private fun onLoadCharacters() = safeLaunch {
        setState(BaseViewState.Loading)
        val params = GetCharacters.Params(config, hashMapOf())
        val pagedFlow = getCharacters(params).cachedIn(scope = viewModelScope)
        setState(BaseViewState.Data(CharactersViewState(pagedData = pagedFlow)))
    }

    private fun onAddOrRemoveFavorite(dto: CharacterDto) = safeLaunch {
        val params = UpdateCharacterFavorite.Params(dto)
        call(updateCharacterFavorite(params))
    }

    private fun onLoadFavorites() = safeLaunch {
        call(getFavorites(Unit)) {
            if (it.isEmpty()) {
                setState(BaseViewState.Empty)
            } else {
                setState(BaseViewState.Data(CharactersViewState(favorList = it)))
            }
        }
    }

    private fun onDeleteFavorite(id: Int) = safeLaunch {
        call(deleteFavorite(DeleteCharacterFavorite.Params(id))) {
            onTriggerEvent(CharactersEvent.LoadFavorites)
        }
    }
}