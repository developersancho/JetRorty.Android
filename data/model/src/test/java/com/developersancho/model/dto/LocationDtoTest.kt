package com.developersancho.model.dto

import com.developersancho.model.dto.location.LocationDto
import org.junit.Assert
import org.junit.Test

class LocationDtoTest {

    @Test
    fun checkCorrectAttributes() {
        val locationId = 1
        val name = "Citadel of Ricks"
        val url = "https://rickandmortyapi.com/api/location/3"

        val dto = LocationDto(
            id = locationId,
            name = name,
            url = url
        )

        Assert.assertEquals(locationId, dto.id)
        Assert.assertEquals(name, dto.name)
        Assert.assertEquals(url, dto.url)
    }
}