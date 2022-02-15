package com.developersancho.model.remote.character

import com.developersancho.testing.BaseModelTest
import org.junit.Assert
import org.junit.Test

class OriginTest : BaseModelTest() {

    override fun checkModelClass(): Class<*> {
        return Origin::class.java
    }

    override fun checkModelFields(): List<String> {
        return listOf(
            "name",
            "url"
        )
    }

    @Test
    fun createResponse() {
        val origin = Origin(
            name = "Earth (C-137)",
            url = "https://rickandmortyapi.com/api/location/1"
        )

        Assert.assertEquals("Earth (C-137)", origin.name)
        Assert.assertEquals("https://rickandmortyapi.com/api/location/1", origin.url)
    }
}