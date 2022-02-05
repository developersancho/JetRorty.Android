package com.developersancho.domain.usecase.character

import androidx.annotation.VisibleForTesting
import com.developersancho.framework.extension.orZero
import com.developersancho.framework.network.DataState
import com.developersancho.framework.network.apiCall
import com.developersancho.framework.usecase.DataStateUseCase
import com.developersancho.model.dto.CharacterDto
import com.developersancho.model.dto.toCharacterDto
import com.developersancho.repository.character.CharacterRepository
import kotlinx.coroutines.flow.FlowCollector

class GetCharacterDetail(
    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val repository: CharacterRepository
) : DataStateUseCase<GetCharacterDetail.Params, CharacterDto>() {

    data class Params(
        val detailId: Int
    )

    override suspend fun FlowCollector<DataState<CharacterDto>>.execute(params: Params) {
        val service = apiCall {
            repository.getCharacter(characterId = params.detailId).toCharacterDto()
        }
        service.map {
            val characterFav = repository.getFavorite(it.id.orZero())
            it.isFavorite = characterFav != null
        }
        emit(service)
    }
}
