package com.developersancho.model.local.character

import com.developersancho.model.remote.base.Status
import org.junit.Assert
import org.junit.Test

class CharacterEntityTest {

    @Test
    fun checkCorrectAttributes() {
        val characterId = 1
        val characterName = "A.I.M"
        val characterImageUrl = "http://i.annihil.us/535fecbbb9784.jpg"

        val entity = CharacterEntity(
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

        Assert.assertEquals(characterId, entity.id)
        Assert.assertEquals(characterName, entity.name)
        Assert.assertEquals(characterImageUrl, entity.imageUrl)
    }
}