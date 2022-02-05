package com.developersancho.domain.usecase.favorite

import androidx.annotation.VisibleForTesting
import com.developersancho.framework.usecase.LocalUseCase
import com.developersancho.model.dto.CharacterDto
import com.developersancho.model.dto.toFavoriteDtoList
import com.developersancho.repository.character.CharacterRepository
import kotlinx.coroutines.flow.FlowCollector

class GetFavorites (
    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val repository: CharacterRepository
) : LocalUseCase<Unit, List<CharacterDto>>() {

    override suspend fun FlowCollector<List<CharacterDto>>.execute(params: Unit) {
        val favors = repository.getFavoriteList()
        emit(favors.toFavoriteDtoList())
    }
}
