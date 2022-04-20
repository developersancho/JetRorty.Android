package com.developersancho.episodes.detail

import app.cash.turbine.test
import com.developersancho.domain.usecase.episode.GetEpisodeDetail
import com.developersancho.framework.base.mvi.BaseViewState
import com.developersancho.framework.network.DataState
import com.developersancho.model.dto.episode.EpisodeDto
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

class EpisodeDetailViewModelTest : MockkUnitTest() {
    @RelaxedMockK
    lateinit var getEpisodeDetail: GetEpisodeDetail

    @SpyK
    @InjectMockKs
    lateinit var viewModel: EpisodeDetailViewModel

    @Test
    fun verifyOnTriggerEventLoadDetailWithDetailId() = runTest {
        // Arrange (Given)
        val detailId = 1

        // Act (When)
        viewModel.onTriggerEvent(EpisodeDetailEvent.LoadDetail(detailId))

        // Assert (Then)
        coVerify { getEpisodeDetail.invoke(GetEpisodeDetail.Params(detailId = detailId)) }
    }

    @Test
    fun verifyOnTriggerEventLoadDetail_CheckState() = runTest {
        // Arrange (Given)
        val detailId = 1
        val dto = mockk<EpisodeDto>()
        val params = GetEpisodeDetail.Params(detailId = detailId)
        coEvery { getEpisodeDetail.invoke(params) } returns flow {
            emit(DataState.Success(dto))
        }

        // Act (When)
        viewModel.onTriggerEvent(EpisodeDetailEvent.LoadDetail(detailId))

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
        coEvery { getEpisodeDetail(any()) } returns flow {
            emit(DataState.Error(IOException("this is a test exception.")))
        }

        // when
        viewModel.onTriggerEvent(EpisodeDetailEvent.LoadDetail(detailId))

        // then
        coVerify(exactly = 1) { getEpisodeDetail(any()) }
        confirmVerified(getEpisodeDetail)

        // assertThrows<RuntimeException> { viewModel.uiState as BaseViewState.Error }
    }
}