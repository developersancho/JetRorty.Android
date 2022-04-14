package com.developersancho.domain.usecase.episode.favorite

import androidx.annotation.VisibleForTesting
import com.developersancho.framework.usecase.LocalUseCase
import com.developersancho.repository.episode.EpisodeRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class DeleteEpisodeFavorite @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val repository: EpisodeRepository
) : LocalUseCase<DeleteEpisodeFavorite.Params, Unit>() {

    data class Params(
        val episodeId: Int
    )

    override suspend fun FlowCollector<Unit>.execute(params: Params) {
        repository.deleteFavoriteById(params.episodeId)
        emit(Unit)
    }
}