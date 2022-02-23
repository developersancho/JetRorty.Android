/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.model.remote.character

import com.developersancho.testing.BaseModelTest
import org.junit.Assert
import org.junit.Test

class LocationTest : BaseModelTest() {

    override fun checkModelClass(): Class<*> {
        return Location::class.java
    }

    override fun checkModelFields(): List<String> {
        return listOf(
            "name",
            "url"
        )
    }

    @Test
    fun createResponse() {
        val location = Location(
            name = "Earth (Replacement Dimension)",
            url = "https://rickandmortyapi.com/api/location/20"
        )

        Assert.assertEquals("Earth (Replacement Dimension)", location.name)
        Assert.assertEquals("https://rickandmortyapi.com/api/location/20", location.url)
    }
}