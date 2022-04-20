package com.developersancho.episodes.list

import androidx.paging.PagingData
import app.cash.turbine.test
import com.developersancho.domain.mockdata.MockData
import com.developersancho.domain.usecase.episode.GetEpisodes
import com.developersancho.domain.usecase.episode.favorite.DeleteEpisodeFavorite
import com.developersancho.domain.usecase.episode.favorite.GetEpisodeFavorites
import com.developersancho.domain.usecase.episode.favorite.UpdateEpisodeFavorite
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

class EpisodesViewModelTest : MockkUnitTest() {
    @RelaxedMockK
    lateinit var getEpisodes: GetEpisodes

    @RelaxedMockK
    lateinit var updateFavorite: UpdateEpisodeFavorite

    @MockK(relaxed = true)
    lateinit var getFavorites: GetEpisodeFavorites

    @MockK(relaxed = true)
    lateinit var deleteFavorite: DeleteEpisodeFavorite

    @SpyK
    @InjectMockKs
    lateinit var viewModel: EpisodesViewModel

    @Test
    fun verifyOnTriggerEventLoadEpisodes() = runTest {
        // Arrange (Given)
        every { getEpisodes.invoke(any()) } returns flow {
            emit(PagingData.from(MockData.getEpisodeDtoList()))
        }

        // Act (When)
        viewModel.onTriggerEvent(EpisodesEvent.LoadEpisodes)

        // Assert (Then)
        verify { getEpisodes.invoke(any()) }
    }

    @Test
    fun verifyOnTriggerEventAddOrRemoveFavorite() = runTest {
        // Arrange (Given)
        val dto = MockData.getEpisodeDto()

        // Act (When)
        viewModel.onTriggerEvent(EpisodesEvent.AddOrRemoveFavorite(dto))

        // Assert (Then)
        coVerify { updateFavorite(UpdateEpisodeFavorite.Params(dto)) }
    }

    @Test
    fun verifyOnTriggerEventGetFavorites() = runTest {
        // Arrange (Given)

        // Act (When)
        viewModel.onTriggerEvent(EpisodesEvent.LoadFavorites)

        // Assert (Then)
        coVerify { getFavorites.invoke(Unit) }
    }

    @Test
    fun verifyOnTriggerEventDeleteFavorite() = runTest {
        // Arrange (Given)
        val id = 1

        // Act (When)
        viewModel.onTriggerEvent(EpisodesEvent.DeleteFavorite(id))

        // Assert (Then)
        coVerify { deleteFavorite(DeleteEpisodeFavorite.Params(id)) }
    }

    @Test
    fun verifyCheckState() = runTest {
        // Arrange (Given)
        val response = PagingData.from(MockData.getEpisodeDtoList())
        every { getEpisodes(any()) } returns flow { emit(response) }

        // Act (When)
        viewModel.onTriggerEvent(EpisodesEvent.LoadEpisodes)

        // Assert (Then)
        viewModel.uiState.test {
            awaitItem().apply {
                Truth.assertThat(this).isNotNull()
                Truth.assertThat(this).isInstanceOf(BaseViewState::class.java)
            }
        }
    }
}