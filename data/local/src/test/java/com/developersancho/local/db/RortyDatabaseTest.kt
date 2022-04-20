package com.developersancho.local.db

import com.developersancho.local.dao.CharacterFavoriteDao
import com.developersancho.local.dao.EpisodeFavoriteDao
import com.developersancho.local.dao.LocationFavoriteDao
import com.developersancho.testutils.TestRobolectric
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test

class RortyDatabaseTest : TestRobolectric() {

    @MockK
    lateinit var database: RortyDatabase

    @MockK
    lateinit var characterFavoriteDao: CharacterFavoriteDao

    @MockK
    lateinit var episodeFavoriteDao: EpisodeFavoriteDao

    @MockK
    lateinit var locationFavoriteDao: LocationFavoriteDao

    @Test
    fun checkCharacterFavoriteDao() {
        every { database.characterFavoriteDao() } returns characterFavoriteDao

        MatcherAssert.assertThat(
            database.characterFavoriteDao(),
            CoreMatchers.instanceOf(CharacterFavoriteDao::class.java)
        )
    }

    @Test
    fun checkEpisodeFavoriteDao() {
        every { database.episodeFavoriteDao() } returns episodeFavoriteDao

        MatcherAssert.assertThat(
            database.episodeFavoriteDao(),
            CoreMatchers.instanceOf(EpisodeFavoriteDao::class.java)
        )
    }

    @Test
    fun checkLocationFavoriteDao() {
        every { database.locationFavoriteDao() } returns locationFavoriteDao

        MatcherAssert.assertThat(
            database.locationFavoriteDao(),
            CoreMatchers.instanceOf(LocationFavoriteDao::class.java)
        )
    }
}