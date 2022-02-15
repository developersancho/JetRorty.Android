@file:Suppress("BlockingMethodInNonBlockingContext")

package com.developersancho.remote.service.location

import com.developersancho.remote.BuildConfig
import com.developersancho.remote.service.LocationService
import com.developersancho.remote.service.mock.MockResponses
import com.developersancho.testing.BaseServiceTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class LocationServiceTest : BaseServiceTest<LocationService>(LocationService::class) {

    override val baseUrl: String
        get() = BuildConfig.BASE_URL

    /**
     * Get Location List
     */
    @Test
    fun requestLiveGetLocations() = runTest {
        val response = serviceLive.getLocationList(page = 1, options = hashMapOf())
        Assert.assertEquals(1, response.results?.first()?.id)
        Assert.assertEquals("Earth (C-137)", response.results?.first()?.name)
        Assert.assertEquals("Planet", response.results?.first()?.type)
    }

    @Test
    fun requestGetLocations() = runTest {
        enqueueResponse(MockResponses.GetLocations.STATUS_200)
        serviceMock.getLocationList(page = 1, options = hashMapOf())
        val request = mockWebServer.takeRequest()
        Assert.assertEquals("GET", request.method)
        Assert.assertEquals("/location?page=1", request.path)
    }

    @Test
    fun responseGetLocations() = runTest {
        enqueueResponse(MockResponses.GetLocations.STATUS_200)
        val response = serviceMock.getLocationList(page = 1, options = hashMapOf())
        Assert.assertEquals(1, response.results?.first()?.id)
        Assert.assertEquals("Earth (C-137)", response.results?.first()?.name)
        Assert.assertEquals("Planet", response.results?.first()?.type)
    }

    /**
     * Get Location List by Filter
     */
    @Test
    fun requestLiveGetLocationsByFilter() = runTest {
        val response = serviceLive.getLocationList(
            page = 1,
            options = hashMapOf(
                "type" to "Planet"
            )
        )
        Assert.assertEquals(1, response.results?.first()?.id)
        Assert.assertEquals("Earth (C-137)", response.results?.first()?.name)
        Assert.assertEquals("Planet", response.results?.first()?.type)
    }

    @Test
    fun requestGetLocationsByFilter() = runTest {
        enqueueResponse(MockResponses.GetLocationsByFilter.STATUS_200)
        serviceMock.getLocationList(
            page = 1,
            options = hashMapOf(
                "type" to "Planet"
            )
        )
        val request = mockWebServer.takeRequest()
        Assert.assertEquals("GET", request.method)
        Assert.assertEquals("/location?page=1&type=Planet", request.path)
    }

    @Test
    fun responseGetEpisodesByFilter() = runTest {
        enqueueResponse(MockResponses.GetLocationsByFilter.STATUS_200)
        val response = serviceMock.getLocationList(
            page = 1,
            options = hashMapOf(
                "type" to "Planet"
            )
        )
        Assert.assertEquals(1, response.results?.first()?.id)
        Assert.assertEquals("Earth (C-137)", response.results?.first()?.name)
        Assert.assertEquals("Planet", response.results?.first()?.type)
    }

    /**
     * Get Location
     */
    @Test
    fun requestLiveGetLocation() = runTest {
        val response = serviceLive.getLocation(locationId = 1)
        Assert.assertEquals(1, response.id)
        Assert.assertEquals("Earth (C-137)", response.name)
        Assert.assertEquals("Planet", response.type)
    }

    @Test
    fun requestGetLocation() = runTest {
        enqueueResponse(MockResponses.GetLocation.STATUS_200)
        serviceMock.getLocation(locationId = 1)
        val request = mockWebServer.takeRequest()
        Assert.assertEquals("GET", request.method)
        Assert.assertEquals("/location/1", request.path)
    }

    @Test
    fun responseGetLocation() = runTest {
        enqueueResponse(MockResponses.GetLocation.STATUS_200)
        val response = serviceMock.getLocation(locationId = 1)
        Assert.assertEquals(1, response.id)
        Assert.assertEquals("Earth (C-137)", response.name)
        Assert.assertEquals("Planet", response.type)
    }
}