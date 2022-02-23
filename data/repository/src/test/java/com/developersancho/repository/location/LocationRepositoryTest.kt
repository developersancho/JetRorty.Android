/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.repository.location

import com.developersancho.remote.service.LocationService
import com.developersancho.testing.BaseMockTest
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class LocationRepositoryTest : BaseMockTest() {

    @MockK(relaxed = true)
    lateinit var service: LocationService
    private lateinit var repository: LocationRepository

    override fun onCreate() {
        super.onCreate()
        repository = LocationRepository(service)
    }

    @Test
    fun getLocations() = runTest {
        // Given
        val locationPage = 1
        val locationOptions = hashMapOf<String, String>()

        val page = slot<Int>()
        val options = slot<Map<String, String>>()

        // When
        repository.getLocationList(
            page = locationPage, options = locationOptions
        )

        // Then
        coVerify {
            service.getLocationList(
                page = capture(page),
                options = capture(options)
            )
        }

        Assert.assertEquals(locationPage, page.captured)
        Assert.assertEquals(locationOptions, options.captured)
    }

    @Test
    fun getLocation() = runTest {
        // Given
        val locationId = 1
        val id = slot<Int>()

        // When
        repository.getLocation(
            locationId = locationId
        )

        // Then
        coVerify {
            service.getLocation(
                locationId = capture(id)
            )
        }

        Assert.assertEquals(locationId, id.captured)
    }
}