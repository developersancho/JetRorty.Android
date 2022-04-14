package com.developersancho.locations.detail

import com.developersancho.model.dto.location.LocationDto

data class LocationDetailState(
    val location: LocationDto? = null
)

sealed class LocationDetailEvent {
    data class LoadDetail(val id: Int) : LocationDetailEvent()
}