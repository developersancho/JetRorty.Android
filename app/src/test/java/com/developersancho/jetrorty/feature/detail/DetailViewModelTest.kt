package com.developersancho.jetrorty.feature.detail

import app.cash.turbine.test
import com.developersancho.domain.usecase.character.GetCharacterDetail
import com.developersancho.framework.base.BaseViewState
import com.developersancho.framework.network.DataState
import com.developersancho.jetrorty.features.detail.DetailEvent
import com.developersancho.jetrorty.features.detail.DetailViewModel
import com.developersancho.jetrorty.features.detail.DetailViewState
import com.developersancho.jetrorty.provider.ResourceProvider
import com.developersancho.model.dto.CharacterDto
import com.developersancho.testing.BaseMockTest
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException

class DetailViewModelTest : BaseMockTest() {

    @MockK(relaxed = true)
    lateinit var getCharacterDetail: GetCharacterDetail

    @MockK(relaxed = true)
    lateinit var resourceProvider: ResourceProvider

    @SpyK
    @InjectMockKs
    lateinit var viewModel: DetailViewModel

    @Test
    fun verifyOnTriggerEventLoadDetail() = runTest {
        // Arrange (Given)
        val detailId = 1

        // Act (When)
        viewModel.onTriggerEvent(DetailEvent.LoadDetail(detailId))

        // Assert (Then)
        coVerify { getCharacterDetail.invoke(GetCharacterDetail.Params(detailId)) }
    }

    @Test
    fun verifyOnTriggerEventLoadDetail_CheckState() = runTest {
        // Arrange (Given)
        val detailId = 1
        val dto = mockk<CharacterDto>()
        val params = GetCharacterDetail.Params(detailId)
        coEvery { getCharacterDetail.invoke(params) } returns flow {
            emit(DataState.Success(dto))
        }

        // Act (When)
        viewModel.onTriggerEvent(DetailEvent.LoadDetail(detailId))

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
        viewModel.onTriggerEvent(DetailEvent.LoadDetail(detailId))

        // then
        coVerify(exactly = 1) { getCharacterDetail(any()) }
        confirmVerified(getCharacterDetail)

        //assertThrows<RuntimeException> { viewModel.uiState as BaseViewState.Error }
    }
}