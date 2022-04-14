package com.developersancho.domain.usecase.character

import androidx.annotation.VisibleForTesting
import com.developersancho.framework.extension.isNull
import com.developersancho.framework.extension.orZero
import com.developersancho.framework.network.DataState
import com.developersancho.framework.network.apiCall
import com.developersancho.framework.usecase.DataStateUseCase
import com.developersancho.model.dto.character.CharacterDto
import com.developersancho.model.dto.character.toCharacterDto
import com.developersancho.model.dto.episode.toEpisodeDto
import com.developersancho.repository.character.CharacterRepository
import com.developersancho.repository.episode.EpisodeRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetCharacterDetail
@Inject
constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val charRepo: CharacterRepository,
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val episodeRepo: EpisodeRepository
) : DataStateUseCase<GetCharacterDetail.Params, CharacterDto>() {

    data class Params(
        val url: String? = null,
        val detailId: Int? = null
    )

    override suspend fun FlowCollector<DataState<CharacterDto>>.execute(params: Params) {
        val getCharacter = if (params.detailId.isNull()) {
            charRepo.getCharacter(url = params.url.orEmpty()).toCharacterDto()
        } else {
            charRepo.getCharacter(characterId = params.detailId.orZero()).toCharacterDto()
        }
        val service = apiCall { getCharacter }
        service.map { characterDto ->
            val characterFav = charRepo.getFavorite(characterDto.id.orZero())
            characterDto.isFavorite = characterFav != null

            characterDto.episodes.forEach {
                val episodeService = apiCall { episodeRepo.getEpisode(it) }
                if (episodeService is DataState.Success) {
                    characterDto.episodeDtoList.add(episodeService.result.toEpisodeDto())
                }
            }
        }

        emit(service)
    }
}