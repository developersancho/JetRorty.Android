package com.developersancho.episodes.list

import androidx.paging.PagingData
import com.developersancho.domain.mockdata.MockData
import com.developersancho.model.dto.episode.EpisodeDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Test

class EpisodesContractTest {
    private lateinit var event: EpisodesEvent

    private lateinit var state: EpisodesState

    @Test
    fun verifyEventLoadEpisodes() {
        event = EpisodesEvent.LoadEpisodes

        val eventLoadDetail = event as EpisodesEvent.LoadEpisodes
        Assert.assertEquals(event, eventLoadDetail)
    }

    @Test
    fun verifyEventAddOrRemoveFavorite() {
        val dto = MockData.getEpisodeDto()
        event = EpisodesEvent.AddOrRemoveFavorite(dto)

        val eventAddOrRemoveFavorite = event as EpisodesEvent.AddOrRemoveFavorite
        Assert.assertEquals(dto, eventAddOrRemoveFavorite.episode)
    }

    @Test
    fun verifyEventLoadFavorites() {
        event = EpisodesEvent.LoadFavorites

        val eventLoadFavorites = event as EpisodesEvent.LoadFavorites
        Assert.assertEquals(event, eventLoadFavorites)
    }

    @Test
    fun verifyEventDeleteFavorite() {
        val id = 1
        event = EpisodesEvent.DeleteFavorite(id)

        val eventDeleteFavorite = event as EpisodesEvent.DeleteFavorite
        Assert.assertEquals(id, eventDeleteFavorite.id)
    }

    @Test
    fun verifyState() {
        val pagedData: Flow<PagingData<EpisodeDto>> =
            flow { emit(PagingData.empty()) }
        state = EpisodesState(pagedData, emptyList())

        Assert.assertEquals(pagedData, state.pagedData)
        Assert.assertEquals(0, state.favorList.size)
    }
}