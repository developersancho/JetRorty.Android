package com.developersancho.local.dao

import androidx.room.Room
import com.developersancho.local.db.RortyDatabase
import com.developersancho.local.mockdata.LocalMockData
import com.developersancho.model.local.location.LocationEntity
import com.developersancho.testutils.TestRobolectric
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert
import org.junit.Test
import java.io.IOException

class LocationFavoriteDaoTest : TestRobolectric() {
    private lateinit var database: RortyDatabase

    private lateinit var dao: LocationFavoriteDao

    override fun onCreate() {
        super.onCreate()
        runTest {
            database = Room
                .inMemoryDatabaseBuilder(context, RortyDatabase::class.java)
                .allowMainThreadQueries()
                .build()
            dao = database.locationFavoriteDao()
        }
    }

    @Throws(IOException::class)
    override fun onDestroy() {
        super.onDestroy()
        database.close()
    }

    @Test
    fun getFavoriteList_WithData() = runTest {
        val fakeFavorite = LocalMockData.getLocationFavoriteList()
        dao.insert(fakeFavorite)
        val favorites = dao.getFavoriteList()
        Assert.assertEquals(fakeFavorite, favorites)
    }

    @Test
    fun getFavoriteList_WithoutData() = runTest {
        val favorites = dao.getFavoriteList()
        Assert.assertTrue(favorites.isNullOrEmpty())
    }

    @Test
    fun getFavoriteList_WithData_ShouldReturnSorted() = runTest {
        val fakeFavorite = LocalMockData.getLocationFavoriteList()
        dao.insert(fakeFavorite)
        Assert.assertEquals(fakeFavorite, dao.getFavoriteList())
    }

    @Test
    fun getFavoriteById_WithoutData_ShouldNotFound() = runTest {
        val fakeFavorite = LocalMockData.getLocationFavoriteList()
        val favoriteToFind = fakeFavorite.first()
        Assert.assertNull(dao.getFavorite(favoriteToFind.id))
    }

    @Test
    fun getFavoriteById_WithData_ShouldFound() = runTest {
        val fakeFavorite = LocalMockData.getLocationFavoriteList()
        dao.insert(fakeFavorite)
        val favoriteToFind = fakeFavorite.first()
        Assert.assertEquals(favoriteToFind, dao.getFavorite(favoriteToFind.id))
    }

    @Test
    fun insertFavorite_ShouldAdd() = runTest {
        val fakeFavorite = LocalMockData.getLocationFavoriteList()
        fakeFavorite.forEach { dao.insert(it) }

        Assert.assertEquals(fakeFavorite, dao.getFavoriteList())
    }

    @Test
    fun deleteFavoriteList_ShouldRemoveAll() = runTest {
        val fakeFavorite = LocalMockData.getLocationFavoriteList()
        dao.insert(fakeFavorite)
        dao.deleteFavoriteList()

        Assert.assertTrue(dao.getFavoriteList().isNullOrEmpty())
    }

    @Test
    fun deleteFavorite_Stored_ShouldRemoveIt() = runTest {
        val fakeFavorite = LocalMockData.getLocationFavoriteList()
        dao.insert(fakeFavorite)

        val favoriteToRemove = fakeFavorite.first()
        dao.delete(favoriteToRemove)

        MatcherAssert.assertThat(
            dao.getFavoriteList(),
            CoreMatchers.not(CoreMatchers.hasItem(favoriteToRemove))
        )
    }

    @Test
    fun deleteFavorite_NoStored_ShouldNotRemoveNothing() = runTest {
        val fakeFavorite = LocalMockData.getLocationFavoriteList()
        dao.insert(fakeFavorite)

        val favoriteToRemove = LocationEntity(
            id = 5, name = "test",
            "", "", "", "", emptyList()
        )
        dao.delete(favoriteToRemove)

        Assert.assertEquals(fakeFavorite, dao.getFavoriteList())
    }

    @Test
    fun deleteFavoriteById_Stored_ShouldRemoveIt() = runTest {
        val fakeFavorite = LocalMockData.getLocationFavoriteList()
        dao.insert(fakeFavorite)

        val favoriteToRemove = fakeFavorite.first()
        dao.deleteFavoriteById(favoriteToRemove.id)

        MatcherAssert.assertThat(
            dao.getFavoriteList(),
            CoreMatchers.not(CoreMatchers.hasItem(favoriteToRemove))
        )
    }

    @Test
    fun deleteFavoriteById_NoStored_ShouldNotRemoveNothing() = runTest {
        val fakeFavorite = LocalMockData.getLocationFavoriteList()
        dao.insert(fakeFavorite)

        val favoriteNoStoredId = 100
        dao.deleteFavoriteById(favoriteNoStoredId)

        Assert.assertEquals(fakeFavorite, dao.getFavoriteList())
    }
}