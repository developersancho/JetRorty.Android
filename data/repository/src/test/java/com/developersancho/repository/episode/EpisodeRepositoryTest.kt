package com.developersancho.repository.episode

import com.developersancho.remote.service.EpisodeService
import com.developersancho.testing.BaseMockTest
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class EpisodeRepositoryTest: BaseMockTest() {

    @MockK(relaxed = true)
    lateinit var service: EpisodeService
    private lateinit var repository: EpisodeRepository

    override fun onCreate() {
        super.onCreate()
        repository = EpisodeRepository(service)
    }

    @Test
    fun getEpisodes() = runTest {
        // Given
        val episodePage = 1
        val episodeOptions = hashMapOf<String, String>()

        val page = slot<Int>()
        val options = slot<Map<String, String>>()

        // When
        repository.getEpisodeList(
            page = episodePage, options = episodeOptions
        )

        // Then
        coVerify {
            service.getEpisodeList(
                page = capture(page),
                options = capture(options)
            )
        }

        Assert.assertEquals(episodePage, page.captured)
        Assert.assertEquals(episodeOptions, options.captured)
    }

    @Test
    fun getEpisode() = runTest {
        // Given
        val episodeId = 1
        val id = slot<Int>()

        // When
        repository.getEpisode(
            episodeId = episodeId
        )

        // Then
        coVerify {
            service.getEpisode(
                episodeId = capture(id)
            )
        }

        Assert.assertEquals(episodeId, id.captured)
    }
}