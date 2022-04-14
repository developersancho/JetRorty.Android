package com.developersancho.domain.usecase.location.favorite

import androidx.annotation.VisibleForTesting
import com.developersancho.framework.usecase.LocalUseCase
import com.developersancho.repository.location.LocationRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class DeleteLocationFavorite @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val repository: LocationRepository
) : LocalUseCase<DeleteLocationFavorite.Params, Unit>() {

    data class Params(
        val locationId: Int
    )

    override suspend fun FlowCollector<Unit>.execute(params: Params) {
        repository.deleteFavoriteById(params.locationId)
        emit(Unit)
    }
}