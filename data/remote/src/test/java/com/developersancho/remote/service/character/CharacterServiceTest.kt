@file:Suppress("BlockingMethodInNonBlockingContext")

package com.developersancho.remote.service.character

import com.developersancho.model.remote.base.Status
import com.developersancho.remote.BuildConfig
import com.developersancho.remote.service.CharacterService
import com.developersancho.remote.service.mock.MockResponses
import com.developersancho.testing.BaseServiceTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class CharacterServiceTest : BaseServiceTest<CharacterService>(CharacterService::class) {

    override val baseUrl: String
        get() = BuildConfig.BASE_URL

    /**
     * Get Character List
     */
    @Test
    fun requestLiveGetCharacters() = runTest {
        val response = serviceLive.getCharacterList(page = 1, options = hashMapOf())
        Assert.assertEquals(1, response.results?.first()?.id)
        Assert.assertEquals("Rick Sanchez", response.results?.first()?.name)
        Assert.assertEquals(Status.Alive, response.results?.first()?.status)
        Assert.assertEquals("Male", response.results?.first()?.gender)
    }

    @Test
    fun requestGetCharacters() = runTest {
        enqueueResponse(MockResponses.GetCharacters.STATUS_200)
        serviceMock.getCharacterList(page = 1, options = hashMapOf())
        val request = mockWebServer.takeRequest()
        Assert.assertEquals("GET", request.method)
        Assert.assertEquals("/character?page=1", request.path)
    }

    @Test
    fun responseGetCharacters() = runTest {
        enqueueResponse(MockResponses.GetCharacters.STATUS_200)
        val response = serviceMock.getCharacterList(page = 1, options = hashMapOf())
        Assert.assertEquals(1, response.results?.first()?.id)
        Assert.assertEquals("Rick Sanchez", response.results?.first()?.name)
        Assert.assertEquals(Status.Alive, response.results?.first()?.status)
        Assert.assertEquals("Male", response.results?.first()?.gender)
    }

    /**
     * Get Character List by Filter
     */
    @Test
    fun requestLiveGetCharactersByFilter() = runTest {
        val response = serviceLive.getCharacterList(
            page = 1,
            options = hashMapOf(
                "name" to "rick",
                "status" to "alive"
            )
        )
        Assert.assertEquals(1, response.results?.first()?.id)
        Assert.assertEquals("Rick Sanchez", response.results?.first()?.name)
        Assert.assertEquals(Status.Alive, response.results?.first()?.status)
        Assert.assertEquals("Male", response.results?.first()?.gender)
    }

    @Test
    fun requestGetCharactersByFilter() = runTest {
        enqueueResponse(MockResponses.GetCharactersByFilter.STATUS_200)
        serviceMock.getCharacterList(
            page = 1,
            options = hashMapOf(
                "name" to "rick",
                "status" to "alive"
            )
        )
        val request = mockWebServer.takeRequest()
        Assert.assertEquals("GET", request.method)
        Assert.assertEquals("/character?page=1&name=rick&status=alive", request.path)
    }

    @Test
    fun responseGetCharactersByFilter() = runTest {
        enqueueResponse(MockResponses.GetCharactersByFilter.STATUS_200)
        val response = serviceMock.getCharacterList(
            page = 1,
            options = hashMapOf(
                "name" to "rick",
                "status" to "alive"
            )
        )
        Assert.assertEquals(1, response.results?.first()?.id)
        Assert.assertEquals("Rick Sanchez", response.results?.first()?.name)
        Assert.assertEquals(Status.Alive, response.results?.first()?.status)
        Assert.assertEquals("Male", response.results?.first()?.gender)
    }

    /**
     * Get Character
     */
    @Test
    fun requestLiveGetCharacter() = runTest {
        val response = serviceLive.getCharacter(characterId = 1)
        Assert.assertEquals(1, response.id)
        Assert.assertEquals("Rick Sanchez", response.name)
        Assert.assertEquals(Status.Alive, response.status)
        Assert.assertEquals("Male", response.gender)
    }

    @Test
    fun requestGetCharacter() = runTest {
        enqueueResponse(MockResponses.GetCharacter.STATUS_200)
        serviceMock.getCharacter(characterId = 1)
        val request = mockWebServer.takeRequest()
        Assert.assertEquals("GET", request.method)
        Assert.assertEquals("/character/1", request.path)
    }

    @Test
    fun responseGetCharacter() = runTest {
        enqueueResponse(MockResponses.GetCharacter.STATUS_200)
        val response = serviceMock.getCharacter(characterId = 1)
        Assert.assertEquals(1, response.id)
        Assert.assertEquals("Rick Sanchez", response.name)
        Assert.assertEquals(Status.Alive, response.status)
        Assert.assertEquals("Male", response.gender)
    }
}