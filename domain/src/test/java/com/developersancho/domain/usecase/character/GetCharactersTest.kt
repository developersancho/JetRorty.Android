/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.domain.usecase.character

import androidx.paging.PagingConfig
import com.developersancho.repository.character.CharacterRepository
import com.developersancho.testing.BaseMockTest
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetCharactersTest : BaseMockTest() {

    @MockK(relaxed = true)
    lateinit var repository: CharacterRepository

    @SpyK
    @InjectMockKs
    private lateinit var getCharacters: GetCharacters

    @Test
    fun verifyExecute() = runTest {
        // Arrange (Given)
        val pagingConfig = PagingConfig(pageSize = 20)
        val params = GetCharacters.Params(pagingConfig, hashMapOf())

        // Act (When)
        getCharacters.invoke(params)

        // Assert (Then)
        coVerify { getCharacters.invoke(any()) }
    }
}