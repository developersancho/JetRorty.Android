/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.domain.usecase.favorite

import androidx.annotation.VisibleForTesting
import com.developersancho.framework.usecase.LocalUseCase
import com.developersancho.model.dto.CharacterDto
import com.developersancho.model.dto.toFavoriteEntity
import com.developersancho.repository.character.CharacterRepository
import kotlinx.coroutines.flow.FlowCollector

class AddFavorite(
    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val repository: CharacterRepository
) : LocalUseCase<AddFavorite.Params, Unit>() {

    data class Params(
        val character: CharacterDto
    )

    override suspend fun FlowCollector<Unit>.execute(params: Params) {
        val dto = params.character
        repository.saveFavorite(dto.toFavoriteEntity())
        emit(Unit)
    }
}