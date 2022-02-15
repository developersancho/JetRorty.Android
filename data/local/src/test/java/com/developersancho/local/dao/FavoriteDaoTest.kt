package com.developersancho.local.dao

import androidx.room.Room
import com.developersancho.local.db.RortyDatabase
import com.developersancho.model.local.FavoriteEntity
import com.developersancho.model.remote.base.Status
import com.developersancho.testing.TestRobolectric
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert
import org.junit.Test
import java.io.IOException

class FavoriteDaoTest : TestRobolectric() {

    private lateinit var database: RortyDatabase

    private lateinit var favoriteDao: FavoriteDao

    private val fakeFavorite = listOf(
        FavoriteEntity(
            id = 0, name = "3-D Man", imageUrl = "http://i.annihil.us/535fecbbb9784.jpg",
            created = "",
            origin = null,
            location = null,
            status = Status.Unknown,
            species = "",
            gender = "",
            type = "",
            url = "",
            episode = emptyList()
        ),
        FavoriteEntity(
            id = 1, name = "A-Bomb (HAS)", imageUrl = "http://i.annihil.us/5232158de5b16.jpg",
            created = "",
            origin = null,
            location = null,
            status = Status.Unknown,
            species = "",
            gender = "",
            type = "",
            url = "",
            episode = emptyList()
        ),
        FavoriteEntity(
            id = 2, name = "A.I.M", imageUrl = "http://i.annihil.us/52602f21f29ec.jpg",
            created = "",
            origin = null,
            location = null,
            status = Status.Unknown,
            species = "",
            gender = "",
            type = "",
            url = "",
            episode = emptyList()
        )
    )

    override fun onCreate() {
        super.onCreate()
        runTest {
            database = Room
                .inMemoryDatabaseBuilder(context, RortyDatabase::class.java)
                .allowMainThreadQueries()
                .build()
            favoriteDao = database.favoriteDao()
        }
    }

    @Throws(IOException::class)
    override fun onDestroy() {
        super.onDestroy()
        database.close()
    }

    @Test
    fun getFavoriteList_WithData() = runTest {
        favoriteDao.insert(fakeFavorite)
        val favorites = favoriteDao.getFavoriteList()
        Assert.assertEquals(fakeFavorite, favorites)
    }

    @Test
    fun getFavoriteList_WithoutData() = runTest {
        val favorites = favoriteDao.getFavoriteList()
        Assert.assertTrue(favorites.isNullOrEmpty())
    }

    @Test
    fun getFavoriteList_WithData_ShouldReturnSorted() = runTest {
        favoriteDao.insert(fakeFavorite)
        Assert.assertEquals(fakeFavorite, favoriteDao.getFavoriteList())
    }

    @Test
    fun getFavoriteById_WithoutData_ShouldNotFound() = runTest {
        val favoriteToFind = fakeFavorite.first()
        Assert.assertNull(favoriteDao.getFavorite(favoriteToFind.id))
    }

    @Test
    fun getFavoriteById_WithData_ShouldFound() = runTest {
        favoriteDao.insert(fakeFavorite)
        val favoriteToFind = fakeFavorite.first()
        Assert.assertEquals(favoriteToFind, favoriteDao.getFavorite(favoriteToFind.id))
    }

    @Test
    fun insertFavorite_ShouldAdd() = runTest {
        fakeFavorite.forEach { favoriteDao.insert(it) }

        Assert.assertEquals(fakeFavorite, favoriteDao.getFavoriteList())
    }

    @Test
    fun deleteFavoriteList_ShouldRemoveAll() = runTest {
        favoriteDao.insert(fakeFavorite)
        favoriteDao.deleteFavoriteList()

        Assert.assertTrue(favoriteDao.getFavoriteList().isNullOrEmpty())
    }

    @Test
    fun deleteFavorite_Stored_ShouldRemoveIt() = runTest {
        favoriteDao.insert(fakeFavorite)

        val favoriteToRemove = fakeFavorite.first()
        favoriteDao.delete(favoriteToRemove)

        MatcherAssert.assertThat(
            favoriteDao.getFavoriteList(),
            CoreMatchers.not(CoreMatchers.hasItem(favoriteToRemove))
        )
    }

    @Test
    fun deleteFavorite_NoStored_ShouldNotRemoveNothing() = runTest {
        favoriteDao.insert(fakeFavorite)

        val favoriteToRemove = FavoriteEntity(
            id = 5, name = "test", imageUrl = "url",
            created = "",
            origin = null,
            location = null,
            status = Status.Unknown,
            species = "",
            gender = "",
            type = "",
            url = "",
            episode = emptyList()
        )
        favoriteDao.delete(favoriteToRemove)

        Assert.assertEquals(fakeFavorite, favoriteDao.getFavoriteList())
    }

    @Test
    fun deleteFavoriteById_Stored_ShouldRemoveIt() = runTest {
        favoriteDao.insert(fakeFavorite)

        val favoriteToRemove = fakeFavorite.first()
        favoriteDao.deleteFavoriteById(favoriteToRemove.id)

        MatcherAssert.assertThat(
            favoriteDao.getFavoriteList(),
            CoreMatchers.not(CoreMatchers.hasItem(favoriteToRemove))
        )
    }

    @Test
    fun deleteFavoriteById_NoStored_ShouldNotRemoveNothing() = runTest {
        favoriteDao.insert(fakeFavorite)

        val favoriteNoStoredId = 100
        favoriteDao.deleteFavoriteById(favoriteNoStoredId)

        Assert.assertEquals(fakeFavorite, favoriteDao.getFavoriteList())
    }
}