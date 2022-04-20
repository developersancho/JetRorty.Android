package com.developersancho.domain.usecase.character.favorite

import com.developersancho.repository.character.CharacterRepository
import com.developersancho.testutils.MockkUnitTest
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DeleteCharacterFavoriteTest : MockkUnitTest() {

    @MockK(relaxed = true)
    lateinit var repository: CharacterRepository

    @SpyK
    @InjectMockKs
    private lateinit var deleteFavorite: DeleteCharacterFavorite

    @Test
    fun verifyExecute() = runTest {
        // Arrange (Given)
        val characterId = 1

        // Act (When)
        val params = DeleteCharacterFavorite.Params(characterId)
        deleteFavorite.invoke(params)

        // Assert (Then)
        coVerify { deleteFavorite.invoke(any()) }
    }

    @Test
    fun collectExecute() = runTest {
        // Arrange (Given)
        val characterId = 1
        val params = DeleteCharacterFavorite.Params(characterId)

        // Act (When)
        deleteFavorite.invoke(params).single()

        // Assert (Then)
        coVerify {
            repository.deleteFavoriteById(characterId)
        }
    }
}