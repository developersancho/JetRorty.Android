package com.developersancho.repository.location

import com.developersancho.local.dao.LocationFavoriteDao
import com.developersancho.model.local.location.LocationEntity
import com.developersancho.remote.service.LocationService
import com.developersancho.repository.mockdata.RepositoryMockData
import com.developersancho.testutils.MockkUnitTest
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class LocationRepositoryTest : MockkUnitTest() {

    @MockK(relaxed = true)
    lateinit var service: LocationService

    @MockK(relaxed = true)
    lateinit var dao: LocationFavoriteDao

    @SpyK
    @InjectMockKs
    private lateinit var repository: LocationRepository

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
        val favoriteToInsert = LocationEntity(
            id = 0,
            name = "A.I.M",
            "", "", "", "", emptyList()
        )

        val entityCaptor = slot<LocationEntity>()
        repository.saveFavorite(favoriteToInsert)

        coVerify { dao.insert(capture(entityCaptor)) }
        Assert.assertEquals(favoriteToInsert, entityCaptor.captured)
    }

    @Test
    fun saveFavoriteList() = runTest {
        val favoritesToInsert = RepositoryMockData.getLocationFavoriteList()
        val entityCaptor = slot<List<LocationEntity>>()
        repository.saveFavoriteList(favoritesToInsert)

        coVerify { dao.insert(capture(entityCaptor)) }
        Assert.assertEquals(favoritesToInsert, entityCaptor.captured)
    }
}