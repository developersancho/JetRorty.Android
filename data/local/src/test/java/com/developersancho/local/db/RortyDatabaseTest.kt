/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.local.db

import com.developersancho.local.dao.FavoriteDao
import com.developersancho.testing.TestRobolectric
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test

class RortyDatabaseTest : TestRobolectric() {

    @MockK
    lateinit var database: RortyDatabase

    @MockK
    lateinit var favoriteDao: FavoriteDao

    @Test
    fun obtainFavoriteDao() {
        every { database.favoriteDao() } returns favoriteDao

        MatcherAssert.assertThat(
            database.favoriteDao(),
            CoreMatchers.instanceOf(FavoriteDao::class.java)
        )
    }
}