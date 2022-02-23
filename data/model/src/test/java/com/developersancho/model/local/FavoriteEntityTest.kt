/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.model.local

import com.developersancho.model.remote.base.Status
import org.junit.Assert
import org.junit.Test

class FavoriteEntityTest {

    @Test
    fun createFavorite_ShouldAddCorrectAttributes() {
        val characterId = 1
        val characterName = "A.I.M"
        val characterImageUrl = "http://i.annihil.us/535fecbbb9784.jpg"

        val characterFavorite = FavoriteEntity(
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
            episode = emptyList()
        )

        Assert.assertEquals(characterId, characterFavorite.id)
        Assert.assertEquals(characterName, characterFavorite.name)
        Assert.assertEquals(characterImageUrl, characterFavorite.imageUrl)
    }
}