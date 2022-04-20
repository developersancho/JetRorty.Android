package com.developersancho.model.local.location

import org.junit.Assert
import org.junit.Test

class LocationEntityTest {
    @Test
    fun checkCorrectAttributes() {
        val id = 1
        val name = "The Ricklantis Mixup"

        val entity = LocationEntity(
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