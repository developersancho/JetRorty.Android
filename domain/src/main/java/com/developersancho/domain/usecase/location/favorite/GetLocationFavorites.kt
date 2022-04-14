package com.developersancho.domain.usecase.location.favorite

import androidx.annotation.VisibleForTesting
import com.developersancho.framework.usecase.LocalUseCase
import com.developersancho.model.dto.location.LocationDto
import com.developersancho.model.dto.location.toFavoriteDtoList
import com.developersancho.repository.location.LocationRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetLocationFavorites @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val repository: LocationRepository
) : LocalUseCase<Unit, List<LocationDto>>() {

    override suspend fun FlowCollector<List<LocationDto>>.execute(params: Unit) {
        val favors = repository.getFavoriteList()
        emit(favors.toFavoriteDtoList())
    }
}