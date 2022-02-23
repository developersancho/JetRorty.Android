/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.model.dto

import org.junit.Assert
import org.junit.Test

class LocationDtoTest {

    @Test
    fun createLocationDto_ShouldAddCorrectAttributes() {
        val locationId = 1
        val name = "A.I.M"
        val url = "http://i.annihil.us/535fecbbb9784.jpg"

        val dto = LocationDto(
            locationId = locationId,
            name = name,
            url = url
        )

        Assert.assertEquals(locationId, dto.locationId)
        Assert.assertEquals(name, dto.name)
        Assert.assertEquals(url, dto.url)
    }
}