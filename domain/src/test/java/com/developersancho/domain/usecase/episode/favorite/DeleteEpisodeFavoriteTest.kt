package com.developersancho.domain.usecase.episode.favorite

import com.developersancho.repository.episode.EpisodeRepository
import com.developersancho.testutils.MockkUnitTest
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DeleteEpisodeFavoriteTest : MockkUnitTest() {
    @MockK(relaxed = true)
    lateinit var repository: EpisodeRepository

    @SpyK
    @InjectMockKs
    private lateinit var deleteFavorite: DeleteEpisodeFavorite

    @Test
    fun verifyExecute() = runTest {
        // Arrange (Given)
        val id = 1

        // Act (When)
        val params = DeleteEpisodeFavorite.Params(id)
        deleteFavorite.invoke(params)

        // Assert (Then)
        coVerify { deleteFavorite.invoke(any()) }
    }

    @Test
    fun collectExecute() = runTest {
        // Arrange (Given)
        val id = 1
        val params = DeleteEpisodeFavorite.Params(id)

        // Act (When)
        deleteFavorite.invoke(params).single()

        // Assert (Then)
        coVerify {
            repository.deleteFavoriteById(id)
        }
    }
}