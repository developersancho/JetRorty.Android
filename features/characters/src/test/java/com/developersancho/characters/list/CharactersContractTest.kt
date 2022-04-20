package com.developersancho.characters.list

import androidx.paging.PagingData
import com.developersancho.domain.mockdata.MockData
import com.developersancho.model.dto.character.CharacterDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Test

class CharactersContractTest {
    private lateinit var event: CharactersEvent

    private lateinit var state: CharactersViewState

    @Test
    fun verifyEventLoadCharacters() {
        event = CharactersEvent.LoadCharacters

        val eventLoadDetail = event as CharactersEvent.LoadCharacters
        Assert.assertEquals(event, eventLoadDetail)
    }

    @Test
    fun verifyEventAddOrRemoveFavorite() {
        val dto = MockData.getCharacterDto()
        event = CharactersEvent.AddOrRemoveFavorite(dto)

        val eventAddOrRemoveFavorite = event as CharactersEvent.AddOrRemoveFavorite
        Assert.assertEquals(dto, eventAddOrRemoveFavorite.characterDto)
    }

    @Test
    fun verifyEventLoadFavorites() {
        event = CharactersEvent.LoadFavorites

        val eventLoadFavorites = event as CharactersEvent.LoadFavorites
        Assert.assertEquals(event, eventLoadFavorites)
    }

    @Test
    fun verifyEventDeleteFavorite() {
        val id = 1
        event = CharactersEvent.DeleteFavorite(id)

        val eventDeleteFavorite = event as CharactersEvent.DeleteFavorite
        Assert.assertEquals(id, eventDeleteFavorite.id)
    }

    @Test
    fun verifyStateCharacters() {
        val pagedData: Flow<PagingData<CharacterDto>> =
            flow { emit(PagingData.empty()) }
        state = CharactersViewState(pagedData, emptyList())

        Assert.assertEquals(pagedData, state.pagedData)
        Assert.assertEquals(0, state.favorList.size)
    }
}