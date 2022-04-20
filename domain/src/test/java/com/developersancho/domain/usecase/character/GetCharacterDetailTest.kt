package com.developersancho.domain.usecase.character

import com.developersancho.repository.character.CharacterRepository
import com.developersancho.repository.episode.EpisodeRepository
import com.developersancho.testutils.MockkUnitTest
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetCharacterDetailTest : MockkUnitTest() {

    @MockK(relaxed = true)
    lateinit var charRepo: CharacterRepository

    @MockK(relaxed = true)
    lateinit var episodeRepo: EpisodeRepository

    @SpyK
    @InjectMockKs
    private lateinit var characterDetail: GetCharacterDetail

    @Test
    fun verifyExecute() = runTest {
        // Arrange (Given)
        val detailId = -1

        // Act (When)
        val params = GetCharacterDetail.Params(detailId = detailId)
        characterDetail.invoke(params)

        // Assert (Then)
        coVerify { characterDetail.invoke(any()) }
    }

    @Test
    fun collectExecute() = runTest {
        // Arrange (Given)
        val detailId = 1

        // Act (When)
        val param = GetCharacterDetail.Params(detailId = detailId)
        characterDetail.invoke(param).single()

        // Assert (Then)
        coVerify { charRepo.getCharacter(characterId = detailId) }
    }
}