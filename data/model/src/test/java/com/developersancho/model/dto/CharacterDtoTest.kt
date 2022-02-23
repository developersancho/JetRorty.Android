/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.model.dto

import com.developersancho.model.remote.base.Status
import org.junit.Assert
import org.junit.Test

class CharacterDtoTest {

    @Test
    fun createCharacterDto_ShouldAddCorrectAttributes() {
        val characterId = 1
        val characterName = "A.I.M"
        val characterImageUrl = "http://i.annihil.us/535fecbbb9784.jpg"

        val dto = CharacterDto(
            id = characterId,
            name = characterName,
            image = characterImageUrl,
            created = "",
            origin = null,
            location = null,
            status = Status.Unknown,
            species = "",
            gender = "",
            type = "",
            url = "",
            episode = emptyList()
        )

        Assert.assertEquals(characterId, dto.id)
        Assert.assertEquals(characterName, dto.name)
        Assert.assertEquals(characterImageUrl, dto.image)
    }
}