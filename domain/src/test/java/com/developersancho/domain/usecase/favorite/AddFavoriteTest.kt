/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.domain.usecase.favorite

import com.developersancho.domain.mockdata.MockData
import com.developersancho.model.dto.CharacterDto
import com.developersancho.model.dto.toFavoriteEntity
import com.developersancho.repository.character.CharacterRepository
import com.developersancho.testing.BaseMockTest
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import io.mockk.mockk
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AddFavoriteTest : BaseMockTest() {

    @MockK(relaxed = true)
    lateinit var repository: CharacterRepository

    @SpyK
    @InjectMockKs
    private lateinit var addFavorite: AddFavorite

    @Test
    fun verifyExecute() = runTest {
        // Arrange (Given)
        val dto = mockk<CharacterDto>()

        // Act (When)
        val params = AddFavorite.Params(dto)
        addFavorite.invoke(params)

        // Assert (Then)
        coVerify { addFavorite.invoke(any()) }
    }

    @Test
    fun collectExecute() = runTest {
        // Arrange (Given)
        val dto = MockData.getCharacterDto()
        val params = AddFavorite.Params(dto)

        // Act (When)
        addFavorite.invoke(params).single()

        // Assert (Then)
        coVerify {
            repository.saveFavorite(dto.toFavoriteEntity())
        }
    }
}