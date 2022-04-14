package com.developersancho.domain.usecase.character.favorite

import androidx.annotation.VisibleForTesting
import com.developersancho.framework.usecase.LocalUseCase
import com.developersancho.repository.character.CharacterRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class DeleteCharacterFavorite @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val repository: CharacterRepository
) : LocalUseCase<DeleteCharacterFavorite.Params, Unit>() {

    data class Params(
        val characterId: Int
    )

    override suspend fun FlowCollector<Unit>.execute(params: Params) {
        repository.deleteFavoriteById(params.characterId)
        emit(Unit)
    }
}