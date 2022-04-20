package com.developersancho.domain.usecase.episode

import com.developersancho.repository.character.CharacterRepository
import com.developersancho.repository.episode.EpisodeRepository
import com.developersancho.testutils.MockkUnitTest
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetEpisodeDetailTest : MockkUnitTest() {

    @RelaxedMockK
    lateinit var charRepo: CharacterRepository

    @RelaxedMockK
    lateinit var episodeRepo: EpisodeRepository

    @SpyK
    @InjectMockKs
    private lateinit var getEpisodeDetail: GetEpisodeDetail

    @Test
    fun verifyExecute() = runTest {
        // Arrange (Given)
        val detailId = -1

        // Act (When)
        val params = GetEpisodeDetail.Params(detailId = detailId)
        getEpisodeDetail.invoke(params)

        // Assert (Then)
        coVerify { getEpisodeDetail.invoke(any()) }
    }
}