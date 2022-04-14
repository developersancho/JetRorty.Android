package com.developersancho.domain.usecase.episode

import androidx.annotation.VisibleForTesting
import com.developersancho.framework.extension.isNull
import com.developersancho.framework.extension.orZero
import com.developersancho.framework.network.DataState
import com.developersancho.framework.network.apiCall
import com.developersancho.framework.usecase.DataStateUseCase
import com.developersancho.model.dto.character.toCharacterDto
import com.developersancho.model.dto.episode.EpisodeDto
import com.developersancho.model.dto.episode.toEpisodeDto
import com.developersancho.repository.character.CharacterRepository
import com.developersancho.repository.episode.EpisodeRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetEpisodeDetail
@Inject
constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val episodeRepo: EpisodeRepository,
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val charRepo: CharacterRepository
) : DataStateUseCase<GetEpisodeDetail.Params, EpisodeDto>() {

    data class Params(
        val url: String? = null,
        val detailId: Int? = null
    )

    override suspend fun FlowCollector<DataState<EpisodeDto>>.execute(params: Params) {
        val getEpisode = if (params.detailId.isNull()) {
            episodeRepo.getEpisode(url = params.url.orEmpty()).toEpisodeDto()
        } else {
            episodeRepo.getEpisode(params.detailId.orZero()).toEpisodeDto()
        }
        val service = apiCall { getEpisode }
        service.map { dto ->
            val favorite = episodeRepo.getFavorite(dto.id.orZero())
            dto.isFavorite = favorite != null

            dto.characters.forEach {
                val characterService = apiCall { charRepo.getCharacter(it) }
                if (characterService is DataState.Success) {
                    dto.characterDtoList.add(characterService.result.toCharacterDto())
                }
            }
        }
        emit(service)
    }
}