package com.developersancho.domain.usecase.episode.favorite

import androidx.annotation.VisibleForTesting
import com.developersancho.framework.extension.orZero
import com.developersancho.framework.usecase.LocalUseCase
import com.developersancho.model.dto.episode.EpisodeDto
import com.developersancho.model.dto.episode.toEpisodeEntity
import com.developersancho.repository.episode.EpisodeRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class UpdateEpisodeFavorite @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val repository: EpisodeRepository
) : LocalUseCase<UpdateEpisodeFavorite.Params, Unit>() {

    data class Params(
        val episode: EpisodeDto
    )

    override suspend fun FlowCollector<Unit>.execute(params: Params) {
        val dto = params.episode
        val character = repository.getFavorite(dto.id.orZero())
        if (character == null) {
            repository.saveFavorite(dto.toEpisodeEntity())
        } else {
            repository.deleteFavoriteById(dto.id.orZero())
        }
        emit(Unit)
    }
}