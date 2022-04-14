package com.developersancho.locations.list

import androidx.paging.PagingData
import com.developersancho.model.dto.location.LocationDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class LocationsState(
    val pagedData: Flow<PagingData<LocationDto>> = emptyFlow(),
    val favorList: List<LocationDto> = emptyList()
)

sealed class LocationsEvent {
    object LoadLocations : LocationsEvent()
    data class AddOrRemoveFavorite(val location: LocationDto) : LocationsEvent()
    object LoadFavorites : LocationsEvent()
    data class DeleteFavorite(val id: Int) : LocationsEvent()
}