package com.developersancho.domain.usecase.episode.favorite

import com.developersancho.domain.mockdata.MockData
import com.developersancho.framework.extension.orZero
import com.developersancho.model.dto.episode.EpisodeDto
import com.developersancho.model.dto.episode.toEpisodeEntity
import com.developersancho.model.local.episode.EpisodeEntity
import com.developersancho.repository.episode.EpisodeRepository
import com.developersancho.testutils.MockkUnitTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import io.mockk.mockk
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Test

class UpdateEpisodeFavoriteTest : MockkUnitTest() {
    @RelaxedMockK
    lateinit var repository: EpisodeRepository

    @SpyK
    @MockK
    lateinit var entity: EpisodeEntity

    @SpyK
    @InjectMockKs
    private lateinit var updateFavorite: UpdateEpisodeFavorite

    @Test
    fun verifyExecute() = runTest {
        // Arrange (Given)
        val dto = mockk<EpisodeDto>()

        // Act (When)
        val params = UpdateEpisodeFavorite.Params(dto)
        updateFavorite.invoke(params)

        // Assert (Then)
        coVerify { updateFavorite.invoke(any()) }
    }

    @Test
    fun collectExecuteSave() = runTest {
        // Arrange (Given)
        val dto = MockData.getEpisodeDto()
        val params = UpdateEpisodeFavorite.Params(dto)

        coEvery { repository.getFavorite(dto.id.orZero()) } returns null

        // Act (When)
        updateFavorite.invoke(params).single()

        // Assert (Then)
        coVerify { repository.saveFavorite(dto.toEpisodeEntity()) }
    }

    @Test
    fun collectExecuteDelete() = runTest {
        // Arrange (Given)
        val dto = MockData.getEpisodeDto()
        val params = UpdateEpisodeFavorite.Params(dto)

        coEvery { repository.getFavorite(dto.id.orZero()) } returns entity

        // Act (When)
        updateFavorite.invoke(params).single()

        // Assert (Then)
        coVerify { repository.deleteFavoriteById(dto.id.orZero()) }
    }
}