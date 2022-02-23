/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.repository.location

import androidx.annotation.VisibleForTesting
import com.developersancho.remote.service.LocationService

class LocationRepository(
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    internal val service: LocationService
) {
    suspend fun getLocationList(
        page: Int,
        options: Map<String, String>
    ) = service.getLocationList(page, options)

    suspend fun getLocation(
        locationId: Int
    ) = service.getLocation(locationId)
}