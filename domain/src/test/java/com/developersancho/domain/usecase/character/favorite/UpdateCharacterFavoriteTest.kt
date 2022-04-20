package com.developersancho.domain.usecase.character.favorite

import com.developersancho.domain.mockdata.MockData
import com.developersancho.framework.extension.orZero
import com.developersancho.model.dto.character.CharacterDto
import com.developersancho.model.dto.character.toCharacterEntity
import com.developersancho.model.local.character.CharacterEntity
import com.developersancho.repository.character.CharacterRepository
import com.developersancho.testutils.MockkUnitTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import io.mockk.mockk
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Test

class UpdateCharacterFavoriteTest : MockkUnitTest() {

    @MockK(relaxed = true)
    lateinit var repository: CharacterRepository

    @SpyK
    @MockK
    lateinit var entity: CharacterEntity

    @SpyK
    @InjectMockKs
    private lateinit var updateFavorite: UpdateCharacterFavorite

    @Test
    fun verifyExecute() = runTest {
        // Arrange (Given)
        val dto = mockk<CharacterDto>()

        // Act (When)
        val params = UpdateCharacterFavorite.Params(dto)
        updateFavorite.invoke(params)

        // Assert (Then)
        coVerify { updateFavorite.invoke(any()) }
    }

    @Test
    fun collectExecuteSave() = runTest {
        // Arrange (Given)
        val dto = MockData.getCharacterDto()
        val params = UpdateCharacterFavorite.Params(dto)

        coEvery { repository.getFavorite(dto.id.orZero()) } returns null

        // Act (When)
        updateFavorite.invoke(params).single()

        // Assert (Then)
        coVerify { repository.saveFavorite(dto.toCharacterEntity()) }
    }

    @Test
    fun collectExecuteDelete() = runTest {
        // Arrange (Given)
        val dto = MockData.getCharacterDto()
        val params = UpdateCharacterFavorite.Params(dto)

        coEvery { repository.getFavorite(dto.id.orZero()) } returns entity

        // Act (When)
        updateFavorite.invoke(params).single()

        // Assert (Then)
        coVerify { repository.deleteFavoriteById(dto.id.orZero()) }
    }
}