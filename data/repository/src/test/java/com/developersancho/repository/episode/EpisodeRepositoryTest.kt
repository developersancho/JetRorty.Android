package com.developersancho.repository.episode

import com.developersancho.local.dao.EpisodeFavoriteDao
import com.developersancho.model.local.episode.EpisodeEntity
import com.developersancho.remote.service.EpisodeService
import com.developersancho.repository.mockdata.RepositoryMockData
import com.developersancho.testutils.MockkUnitTest
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class EpisodeRepositoryTest : MockkUnitTest() {

    @MockK(relaxed = true)
    lateinit var service: EpisodeService

    @MockK(relaxed = true)
    lateinit var dao: EpisodeFavoriteDao

    private lateinit var repository: EpisodeRepository

    override fun onCreate() {
        super.onCreate()
        repository = EpisodeRepository(service, dao)
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

    @Test
    fun getFavoriteList() = runTest {
        repository.getFavoriteList()

        coVerify { dao.getFavoriteList() }
    }

    @Test
    fun getFavorite() = runTest {
        val idToFind = 1
        val idCaptor = slot<Int>()

        repository.getFavorite(idToFind)

        coVerify { dao.getFavorite(capture(idCaptor)) }

        Assert.assertEquals(idToFind, idCaptor.captured)
    }

    @Test
    fun deleteFavoriteById() = runTest {
        val idToDelete = 1
        val idCaptor = slot<Int>()
        repository.deleteFavoriteById(idToDelete)

        coVerify {
            dao.deleteFavoriteById(capture(idCaptor))
        }
        Assert.assertEquals(idToDelete, idCaptor.captured)
    }

    @Test
    fun saveFavorite() = runTest {
        val favoriteToInsert = EpisodeEntity(
            id = 0,
            name = "A.I.M",
            "", "", "", "", emptyList()
        )

        val entityCaptor = slot<EpisodeEntity>()
        repository.saveFavorite(favoriteToInsert)

        coVerify { dao.insert(capture(entityCaptor)) }
        Assert.assertEquals(favoriteToInsert, entityCaptor.captured)
    }

    @Test
    fun saveFavoriteList() = runTest {
        val favoritesToInsert = RepositoryMockData.getEpisodeFavoriteList()
        val entityCaptor = slot<List<EpisodeEntity>>()
        repository.saveFavoriteList(favoritesToInsert)

        coVerify { dao.insert(capture(entityCaptor)) }
        Assert.assertEquals(favoritesToInsert, entityCaptor.captured)
    }
}