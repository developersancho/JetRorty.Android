package com.developersancho.domain.usecase.episode.favorite

import com.developersancho.domain.mockdata.MockData
import com.developersancho.model.dto.episode.EpisodeDto
import com.developersancho.model.dto.episode.toEpisodeEntity
import com.developersancho.repository.episode.EpisodeRepository
import com.developersancho.testutils.MockkUnitTest
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import io.mockk.mockk
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AddEpisodeFavoriteTest : MockkUnitTest() {
    @RelaxedMockK
    lateinit var repository: EpisodeRepository

    @SpyK
    @InjectMockKs
    private lateinit var addFavorite: AddEpisodeFavorite

    @Test
    fun verifyExecute() = runTest {
        // Arrange (Given)
        val dto = mockk<EpisodeDto>()

        // Act (When)
        val params = AddEpisodeFavorite.Params(dto)
        addFavorite.invoke(params)

        // Assert (Then)
        coVerify { addFavorite.invoke(any()) }
    }

    @Test
    fun collectExecute() = runTest {
        // Arrange (Given)
        val dto = MockData.getEpisodeDto()
        val params = AddEpisodeFavorite.Params(dto)

        // Act (When)
        addFavorite.invoke(params).single()

        // Assert (Then)
        coVerify {
            repository.saveFavorite(dto.toEpisodeEntity())
        }
    }
}