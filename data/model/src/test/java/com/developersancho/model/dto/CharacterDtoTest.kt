package com.developersancho.model.dto

import com.developersancho.model.dto.character.CharacterDto
import com.developersancho.model.remote.base.Status
import org.junit.Assert
import org.junit.Test

class CharacterDtoTest {

    @Test
    fun checkCorrectAttributes() {
        val characterId = 1
        val characterName = "A.I.M"
        val characterImageUrl = "http://i.annihil.us/535fecbbb9784.jpg"

        val dto = CharacterDto(
            id = characterId,
            name = characterName,
            imageUrl = characterImageUrl,
            created = "",
            origin = null,
            location = null,
            status = Status.Unknown,
            species = "",
            gender = "",
            type = "",
            url = "",
            episodes = emptyList()
        )

        Assert.assertEquals(characterId, dto.id)
        Assert.assertEquals(characterName, dto.name)
        Assert.assertEquals(characterImageUrl, dto.imageUrl)
    }
}