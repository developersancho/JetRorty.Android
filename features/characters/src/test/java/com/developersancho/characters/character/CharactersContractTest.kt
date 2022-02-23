/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.characters.character

import androidx.paging.PagingData
import com.developersancho.characters.CharactersEvent
import com.developersancho.characters.CharactersViewState
import com.developersancho.characters.mockdata.FeatureMockData
import com.developersancho.model.dto.CharacterDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Test

class CharactersContractTest {
    private lateinit var event: CharactersEvent

    private lateinit var state: CharactersViewState

    @Test
    fun verifyEventLoadCharacters_ShouldSettledCorrectly() {
        event = CharactersEvent.LoadCharacters

        val eventLoadDetail = event as CharactersEvent.LoadCharacters
        Assert.assertEquals(event, eventLoadDetail)
    }

    @Test
    fun verifyEventUpdateFavorite_ShouldSettledCorrectly() {
        val dto = FeatureMockData.getCharacterDto()
        event = CharactersEvent.UpdateFavorite(dto)

        val eventLoadDetail = event as CharactersEvent.UpdateFavorite
        Assert.assertEquals(dto, eventLoadDetail.characterDto)
    }

    @Test
    fun verifyStateCharacters_ShouldSettledCorrectly() {
        val pagedData: Flow<PagingData<CharacterDto>> =
            flow { emit(PagingData.empty<CharacterDto>()) }
        state = CharactersViewState(pagedData)

        Assert.assertEquals(pagedData, state.pagedData)
    }
}