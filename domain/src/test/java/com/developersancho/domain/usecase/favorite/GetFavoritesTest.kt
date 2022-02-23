/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.domain.usecase.favorite

import com.developersancho.repository.character.CharacterRepository
import com.developersancho.testing.BaseMockTest
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetFavoritesTest : BaseMockTest() {

    @MockK(relaxed = true)
    lateinit var repository: CharacterRepository

    @SpyK
    @InjectMockKs
    private lateinit var getFavorites: GetFavorites

    @Test
    fun verifyExecute() = runTest {
        // Arrange (Given)

        // Act (When)
        getFavorites.invoke(Unit)

        // Assert (Then)
        coVerify { getFavorites.invoke(Unit) }
    }

    @Test
    fun collectExecute() = runTest {
        // Arrange (Given)

        // Act (When)
        getFavorites.invoke(Unit).single()

        // Assert (Then)
        coVerify { repository.getFavoriteList() }
    }
}