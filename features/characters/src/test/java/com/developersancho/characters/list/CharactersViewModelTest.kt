package com.developersancho.characters.list

import androidx.paging.PagingData
import app.cash.turbine.test
import com.developersancho.domain.mockdata.MockData
import com.developersancho.domain.usecase.character.GetCharacters
import com.developersancho.domain.usecase.character.favorite.DeleteCharacterFavorite
import com.developersancho.domain.usecase.character.favorite.GetCharacterFavorites
import com.developersancho.domain.usecase.character.favorite.UpdateCharacterFavorite
import com.developersancho.framework.base.mvi.BaseViewState
import com.developersancho.testutils.MockkUnitTest
import com.google.common.truth.Truth
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

class CharactersViewModelTest : MockkUnitTest() {

    @RelaxedMockK
    lateinit var getCharacters: GetCharacters

    @RelaxedMockK
    lateinit var updateCharacterFavorite: UpdateCharacterFavorite

    @MockK(relaxed = true)
    lateinit var getFavorites: GetCharacterFavorites

    @MockK(relaxed = true)
    lateinit var deleteFavorite: DeleteCharacterFavorite

    @SpyK
    @InjectMockKs
    lateinit var viewModel: CharactersViewModel

    @Test
    fun verifyOnTriggerEventLoadCharacters() = runTest {
        // Arrange (Given)
        every { getCharacters.invoke(any()) } returns flow {
            emit(PagingData.from(MockData.getCharacterDtoList()))
        }

        // Act (When)
        viewModel.onTriggerEvent(CharactersEvent.LoadCharacters)

        // Assert (Then)
        verify { getCharacters.invoke(any()) }
    }

    @Test
    fun verifyOnTriggerEventAddOrRemoveFavorite() = runTest {
        // Arrange (Given)
        val dto = MockData.getCharacterDto()

        // Act (When)
        viewModel.onTriggerEvent(CharactersEvent.AddOrRemoveFavorite(dto))

        // Assert (Then)
        coVerify { updateCharacterFavorite(UpdateCharacterFavorite.Params(dto)) }
    }

    @Test
    fun verifyOnTriggerEventGetCharacterFavorites() = runTest {
        // Arrange (Given)

        // Act (When)
        viewModel.onTriggerEvent(CharactersEvent.LoadFavorites)

        // Assert (Then)
        coVerify { getFavorites.invoke(Unit) }
    }

    @Test
    fun verifyOnTriggerEventDeleteFavorite() = runTest {
        // Arrange (Given)
        val id = 1

        // Act (When)
        viewModel.onTriggerEvent(CharactersEvent.DeleteFavorite(id))

        // Assert (Then)
        coVerify { deleteFavorite(DeleteCharacterFavorite.Params(id)) }
    }

    @Test
    fun verifyOnTriggerEventLoadCharacters_CheckState() = runTest {
        // Arrange (Given)
        val response = PagingData.from(MockData.getCharacterDtoList())
        every { getCharacters(any()) } returns flow { emit(response) }

        // Act (When)
        viewModel.onTriggerEvent(CharactersEvent.LoadCharacters)

        // Assert (Then)
        viewModel.uiState.test {
            awaitItem().apply {
                Truth.assertThat(this).isNotNull()
                Truth.assertThat(this).isInstanceOf(BaseViewState::class.java)
            }
        }
    }
}