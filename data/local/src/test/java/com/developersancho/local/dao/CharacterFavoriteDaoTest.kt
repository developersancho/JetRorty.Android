package com.developersancho.local.dao

import androidx.room.Room
import com.developersancho.local.db.RortyDatabase
import com.developersancho.local.mockdata.LocalMockData
import com.developersancho.model.local.character.CharacterEntity
import com.developersancho.model.remote.base.Status
import com.developersancho.testutils.TestRobolectric
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert
import org.junit.Test
import java.io.IOException

class CharacterFavoriteDaoTest : TestRobolectric() {
    private lateinit var database: RortyDatabase

    private lateinit var dao: CharacterFavoriteDao

    override fun onCreate() {
        super.onCreate()
        runTest {
            database = Room
                .inMemoryDatabaseBuilder(context, RortyDatabase::class.java)
                .allowMainThreadQueries()
                .build()
            dao = database.characterFavoriteDao()
        }
    }

    @Throws(IOException::class)
    override fun onDestroy() {
        super.onDestroy()
        database.close()
    }

    @Test
    fun getFavoriteList_WithData() = runTest {
        val fakeFavorite = LocalMockData.getCharacterFavoriteList()
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
        val fakeFavorite = LocalMockData.getCharacterFavoriteList()
        dao.insert(fakeFavorite)
        Assert.assertEquals(fakeFavorite, dao.getFavoriteList())
    }

    @Test
    fun getFavoriteById_WithoutData_ShouldNotFound() = runTest {
        val fakeFavorite = LocalMockData.getCharacterFavoriteList()
        val favoriteToFind = fakeFavorite.first()
        Assert.assertNull(dao.getFavorite(favoriteToFind.id))
    }

    @Test
    fun getFavoriteById_WithData_ShouldFound() = runTest {
        val fakeFavorite = LocalMockData.getCharacterFavoriteList()
        dao.insert(fakeFavorite)
        val favoriteToFind = fakeFavorite.first()
        Assert.assertEquals(favoriteToFind, dao.getFavorite(favoriteToFind.id))
    }

    @Test
    fun insertFavorite_ShouldAdd() = runTest {
        val fakeFavorite = LocalMockData.getCharacterFavoriteList()
        fakeFavorite.forEach { dao.insert(it) }

        Assert.assertEquals(fakeFavorite, dao.getFavoriteList())
    }

    @Test
    fun deleteFavoriteList_ShouldRemoveAll() = runTest {
        val fakeFavorite = LocalMockData.getCharacterFavoriteList()
        dao.insert(fakeFavorite)
        dao.deleteFavoriteList()

        Assert.assertTrue(dao.getFavoriteList().isNullOrEmpty())
    }

    @Test
    fun deleteFavorite_Stored_ShouldRemoveIt() = runTest {
        val fakeFavorite = LocalMockData.getCharacterFavoriteList()
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
        val fakeFavorite = LocalMockData.getCharacterFavoriteList()
        dao.insert(fakeFavorite)

        val favoriteToRemove = CharacterEntity(
            id = 5, name = "test", imageUrl = "url",
            created = "",
            origin = null,
            location = null,
            status = Status.Unknown,
            species = "",
            gender = "",
            type = "",
            url = "",
            episodes = emptyList()
        )
        dao.delete(favoriteToRemove)

        Assert.assertEquals(fakeFavorite, dao.getFavoriteList())
    }

    @Test
    fun deleteFavoriteById_Stored_ShouldRemoveIt() = runTest {
        val fakeFavorite = LocalMockData.getCharacterFavoriteList()
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
        val fakeFavorite = LocalMockData.getCharacterFavoriteList()
        dao.insert(fakeFavorite)

        val favoriteNoStoredId = 100
        dao.deleteFavoriteById(favoriteNoStoredId)

        Assert.assertEquals(fakeFavorite, dao.getFavoriteList())
    }
}