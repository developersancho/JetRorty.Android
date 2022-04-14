package com.developersancho.episodes.detail

import com.developersancho.model.dto.episode.EpisodeDto

data class EpisodeDetailState(
    val episode: EpisodeDto? = null
)

sealed class EpisodeDetailEvent {
    data class LoadDetail(val id: Int) : EpisodeDetailEvent()
}