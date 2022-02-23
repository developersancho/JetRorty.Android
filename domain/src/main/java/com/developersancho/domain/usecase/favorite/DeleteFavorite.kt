/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.domain.usecase.favorite

import androidx.annotation.VisibleForTesting
import com.developersancho.framework.usecase.LocalUseCase
import com.developersancho.repository.character.CharacterRepository
import kotlinx.coroutines.flow.FlowCollector

class DeleteFavorite(
    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val repository: CharacterRepository
) : LocalUseCase<DeleteFavorite.Params, Unit>() {

    data class Params(
        val characterId: Int
    )

    override suspend fun FlowCollector<Unit>.execute(params: Params) {
        repository.deleteFavoriteById(params.characterId)
        emit(Unit)
    }
}