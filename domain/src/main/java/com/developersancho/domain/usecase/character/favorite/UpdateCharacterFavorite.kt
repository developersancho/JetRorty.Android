package com.developersancho.domain.usecase.character.favorite

import androidx.annotation.VisibleForTesting
import com.developersancho.framework.extension.orZero
import com.developersancho.framework.usecase.LocalUseCase
import com.developersancho.model.dto.character.CharacterDto
import com.developersancho.model.dto.character.toCharacterEntity
import com.developersancho.repository.character.CharacterRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class UpdateCharacterFavorite @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val repository: CharacterRepository
) : LocalUseCase<UpdateCharacterFavorite.Params, Unit>() {

    data class Params(
        val character: CharacterDto
    )

    override suspend fun FlowCollector<Unit>.execute(params: Params) {
        val dto = params.character
        val character = repository.getFavorite(dto.id.orZero())
        if (character == null) {
            repository.saveFavorite(dto.toCharacterEntity())
        } else {
            repository.deleteFavoriteById(dto.id.orZero())
        }
        emit(Unit)
    }
}