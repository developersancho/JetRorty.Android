package com.developersancho.episodes.list

import androidx.paging.PagingData
import com.developersancho.model.dto.episode.EpisodeDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class EpisodesState(
    val pagedData: Flow<PagingData<EpisodeDto>> = emptyFlow(),
    val favorList: List<EpisodeDto> = emptyList()
)

sealed class EpisodesEvent {
    object LoadEpisodes : EpisodesEvent()
    data class AddOrRemoveFavorite(val episode: EpisodeDto) : EpisodesEvent()
    object LoadFavorites : EpisodesEvent()
    data class DeleteFavorite(val id: Int) : EpisodesEvent()
}