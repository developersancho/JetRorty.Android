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

class DeleteFavoriteTest : BaseMockTest() {

    @MockK(relaxed = true)
    lateinit var repository: CharacterRepository

    @SpyK
    @InjectMockKs
    private lateinit var deleteFavorite: DeleteFavorite

    @Test
    fun verifyExecute() = runTest {
        // Arrange (Given)
        val characterId = 1

        // Act (When)
        val params = DeleteFavorite.Params(characterId)
        deleteFavorite.invoke(params)

        // Assert (Then)
        coVerify { deleteFavorite.invoke(any()) }
    }

    @Test
    fun collectExecute() = runTest {
        // Arrange (Given)
        val characterId = 1
        val params = DeleteFavorite.Params(characterId)

        // Act (When)
        deleteFavorite.invoke(params).single()

        // Assert (Then)
        coVerify {
            repository.deleteFavoriteById(characterId)
        }
    }
}