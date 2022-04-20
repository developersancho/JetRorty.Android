package com.developersancho.model.dto

import com.developersancho.model.dto.episode.EpisodeDto
import org.junit.Assert
import org.junit.Test

class EpisodeDtoTest {
    @Test
    fun checkCorrectAttributes() {
        val id = 1
        val name = "Citadel of Ricks"
        val url = "https://rickandmortyapi.com/api/location/3"

        val dto = EpisodeDto(
            id = id,
            name = name,
            url = url,
            "",
            "",
            "",
            emptyList()
        )

        Assert.assertEquals(id, dto.id)
        Assert.assertEquals(name, dto.name)
        Assert.assertEquals(url, dto.url)
    }
}