package com.developersancho.characters.detail

import app.cash.turbine.test
import com.developersancho.domain.usecase.character.GetCharacterDetail
import com.developersancho.framework.base.mvi.BaseViewState
import com.developersancho.framework.network.DataState
import com.developersancho.model.dto.character.CharacterDto
import com.developersancho.testutils.MockkUnitTest
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException

class CharacterDetailViewModelTest : MockkUnitTest() {

    @RelaxedMockK
    lateinit var getCharacterDetail: GetCharacterDetail

    @SpyK
    @InjectMockKs
    lateinit var viewModel: CharacterDetailViewModel

    @Test
    fun verifyOnTriggerEventLoadDetailWithDetailId() = runTest {
        // Arrange (Given)
        val detailId = 1

        // Act (When)
        viewModel.onTriggerEvent(CharacterDetailEvent.LoadDetail(detailId))

        // Assert (Then)
        coVerify { getCharacterDetail.invoke(GetCharacterDetail.Params(detailId = detailId)) }
    }

    @Test
    fun verifyOnTriggerEventLoadDetail_CheckState() = runTest {
        // Arrange (Given)
        val detailId = 1
        val dto = mockk<CharacterDto>()
        val params = GetCharacterDetail.Params(detailId = detailId)
        coEvery { getCharacterDetail.invoke(params) } returns flow {
            emit(DataState.Success(dto))
        }

        // Act (When)
        viewModel.onTriggerEvent(CharacterDetailEvent.LoadDetail(detailId))

        // Assert (Then)
        viewModel.uiState.test {
            awaitItem().apply {
                Truth.assertThat(this).isNotNull()
                Truth.assertThat(this).isInstanceOf(BaseViewState::class.java)
            }
        }
    }

    @Test
    fun verifyOnTriggerEventLoadDetail_CheckError() = runTest {
        // given
        val detailId = 1
        coEvery { getCharacterDetail(any()) } returns flow {
            emit(DataState.Error(IOException("this is a test exception.")))
        }

        // when
        viewModel.onTriggerEvent(CharacterDetailEvent.LoadDetail(detailId))

        // then
        coVerify(exactly = 1) { getCharacterDetail(any()) }
        confirmVerified(getCharacterDetail)

        // assertThrows<RuntimeException> { viewModel.uiState as BaseViewState.Error }
    }
}