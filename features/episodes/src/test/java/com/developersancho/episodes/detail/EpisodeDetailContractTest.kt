package com.developersancho.episodes.detail

import com.developersancho.domain.mockdata.MockData
import org.junit.Assert
import org.junit.Test

class EpisodeDetailContractTest {
    private lateinit var event: EpisodeDetailEvent

    private lateinit var state: EpisodeDetailState

    @Test
    fun verifyEventLoadDetail() {
        val id = 1
        event = EpisodeDetailEvent.LoadDetail(id)

        val eventLoadDetail = event as EpisodeDetailEvent.LoadDetail
        Assert.assertEquals(id, eventLoadDetail.id)
    }

    @Test
    fun verifyState() {
        val dto = MockData.getEpisodeDto()
        state = EpisodeDetailState(dto)

        Assert.assertEquals(dto, state.episode)
    }
}