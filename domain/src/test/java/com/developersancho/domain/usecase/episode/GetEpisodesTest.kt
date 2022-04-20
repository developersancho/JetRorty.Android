package com.developersancho.domain.usecase.episode

import androidx.paging.PagingConfig
import com.developersancho.repository.episode.EpisodeRepository
import com.developersancho.testutils.MockkUnitTest
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetEpisodesTest : MockkUnitTest() {
    @MockK(relaxed = true)
    lateinit var repository: EpisodeRepository

    @SpyK
    @InjectMockKs
    private lateinit var getEpisodes: GetEpisodes

    @Test
    fun verifyExecute() = runTest {
        // Arrange (Given)
        val pagingConfig = PagingConfig(pageSize = 20)
        val params = GetEpisodes.Params(pagingConfig, hashMapOf())

        // Act (When)
        getEpisodes.invoke(params)

        // Assert (Then)
        coVerify { getEpisodes.invoke(any()) }
    }
}