/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.remote.service.episode

import com.developersancho.remote.BuildConfig
import com.developersancho.remote.service.EpisodeService
import com.developersancho.remote.service.mock.MockResponses
import com.developersancho.testing.BaseServiceTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class EpisodeServiceTest : BaseServiceTest<EpisodeService>(EpisodeService::class) {

    override val baseUrl: String
        get() = BuildConfig.BASE_URL

    /**
     * Get Episode List
     */
    @Test
    fun requestLiveGetEpisodes() = runTest {
        val response = serviceLive.getEpisodeList(page = 1, options = hashMapOf())
        Assert.assertEquals(1, response.results?.first()?.id)
        Assert.assertEquals("Pilot", response.results?.first()?.name)
        Assert.assertEquals("S01E01", response.results?.first()?.episode)
    }

    @Test
    fun requestGetEpisodes() = runTest {
        enqueueResponse(MockResponses.GetEpisodes.STATUS_200)
        serviceMock.getEpisodeList(page = 1, options = hashMapOf())
        val request = mockWebServer.takeRequest()
        Assert.assertEquals("GET", request.method)
        Assert.assertEquals("/episode?page=1", request.path)
    }

    @Test
    fun responseGetEpisodes() = runTest {
        enqueueResponse(MockResponses.GetEpisodes.STATUS_200)
        val response = serviceMock.getEpisodeList(page = 1, options = hashMapOf())
        Assert.assertEquals(1, response.results?.first()?.id)
        Assert.assertEquals("Pilot", response.results?.first()?.name)
        Assert.assertEquals("S01E01", response.results?.first()?.episode)
    }

    /**
     * Get Episode List by Filter
     */
    @Test
    fun requestLiveGetEpisodesByFilter() = runTest {
        val response = serviceLive.getEpisodeList(
            page = 1,
            options = hashMapOf(
                "name" to "Pilot"
            )
        )
        Assert.assertEquals(1, response.results?.first()?.id)
        Assert.assertEquals("Pilot", response.results?.first()?.name)
        Assert.assertEquals("S01E01", response.results?.first()?.episode)
    }

    @Test
    fun requestGetEpisodesByFilter() = runTest {
        enqueueResponse(MockResponses.GetEpisodesByFilter.STATUS_200)
        serviceMock.getEpisodeList(
            page = 1,
            options = hashMapOf(
                "name" to "Pilot"
            )
        )
        val request = mockWebServer.takeRequest()
        Assert.assertEquals("GET", request.method)
        Assert.assertEquals("/episode?page=1&name=Pilot", request.path)
    }

    @Test
    fun responseGetEpisodesByFilter() = runTest {
        enqueueResponse(MockResponses.GetEpisodesByFilter.STATUS_200)
        val response = serviceMock.getEpisodeList(
            page = 1,
            options = hashMapOf(
                "name" to "Pilot"
            )
        )
        Assert.assertEquals(1, response.results?.first()?.id)
        Assert.assertEquals("Pilot", response.results?.first()?.name)
        Assert.assertEquals("S01E01", response.results?.first()?.episode)
    }

    /**
     * Get Episode
     */
    @Test
    fun requestLiveGetEpisode() = runTest {
        val response = serviceLive.getEpisode(episodeId = 1)
        Assert.assertEquals(1, response.id)
        Assert.assertEquals("Pilot", response.name)
        Assert.assertEquals("S01E01", response.episode)
    }

    @Test
    fun requestGetEpisode() = runTest {
        enqueueResponse(MockResponses.GetEpisode.STATUS_200)
        serviceMock.getEpisode(episodeId = 1)
        val request = mockWebServer.takeRequest()
        Assert.assertEquals("GET", request.method)
        Assert.assertEquals("/episode/1", request.path)
    }

    @Test
    fun responseGetEpisode() = runTest {
        enqueueResponse(MockResponses.GetEpisode.STATUS_200)
        val response = serviceMock.getEpisode(episodeId = 1)
        Assert.assertEquals(1, response.id)
        Assert.assertEquals("Pilot", response.name)
        Assert.assertEquals("S01E01", response.episode)
    }
}