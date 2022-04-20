package com.developersancho.local.di

import android.content.Context
import com.developersancho.local.BuildConfig
import com.developersancho.local.dao.CharacterFavoriteDao
import com.developersancho.local.dao.EpisodeFavoriteDao
import com.developersancho.local.dao.LocationFavoriteDao
import com.developersancho.local.db.RortyDatabase
import com.developersancho.testutils.MockkUnitTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

class LocalModuleTest : MockkUnitTest() {

    private lateinit var localModule: LocalModule

    override fun onCreate() {
        super.onCreate()
        localModule = LocalModule()
    }

    @Test
    fun verifyProvideDatabaseName() {
        val databaseName = localModule.provideDatabaseName()
        assertEquals(databaseName, BuildConfig.DB_NAME)
    }

    @Test
    fun verifyProvideDatabase() {
        val context: Context = mockk()
        val databaseName = localModule.provideDatabaseName()
        val database = localModule.provideDatabase(databaseName, context)

        Assert.assertNotNull(database.characterFavoriteDao())
        Assert.assertNotNull(database.episodeFavoriteDao())
        Assert.assertNotNull(database.locationFavoriteDao())
    }

    @Test
    fun verifyProvideCharacterFavoriteDao() {
        val database: RortyDatabase = mockk()
        val dao: CharacterFavoriteDao = mockk()

        every { database.characterFavoriteDao() } returns dao

        assertEquals(
            dao,
            localModule.provideCharacterFavoriteDao(database)
        )
        verify { database.characterFavoriteDao() }
    }

    @Test
    fun verifyProvideEpisodeFavoriteDao() {
        val database: RortyDatabase = mockk()
        val dao: EpisodeFavoriteDao = mockk()

        every { database.episodeFavoriteDao() } returns dao

        assertEquals(
            dao,
            localModule.provideEpisodeFavoriteDao(database)
        )
        verify { database.episodeFavoriteDao() }
    }

    @Test
    fun verifyProvideLocationFavoriteDao() {
        val database: RortyDatabase = mockk()
        val dao: LocationFavoriteDao = mockk()

        every { database.locationFavoriteDao() } returns dao

        assertEquals(
            dao,
            localModule.provideLocationFavoriteDao(database)
        )
        verify { database.locationFavoriteDao() }
    }
}