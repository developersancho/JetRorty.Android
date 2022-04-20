package com.developersancho.model.local.episode

import org.junit.Assert
import org.junit.Test

class EpisodeEntityTest {
    @Test
    fun checkCorrectAttributes() {
        val id = 1
        val name = "The Ricklantis Mixup"

        val entity = EpisodeEntity(
            id = id,
            name = name,
            "",
            "",
            "",
            "",
            emptyList()
        )

        Assert.assertEquals(id, entity.id)
        Assert.assertEquals(name, entity.name)
    }
}