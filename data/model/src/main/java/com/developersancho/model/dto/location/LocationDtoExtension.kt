package com.developersancho.model.dto.location

import com.developersancho.framework.extension.orZero
import com.developersancho.model.local.location.LocationEntity
import com.developersancho.model.remote.location.LocationInfo

fun LocationInfo.toLocationDto() = LocationDto(
    id = id.orZero(),
    name = name.orEmpty(),
    url = url,
    dimension = dimension,
    created = created,
    type = type,
    residents = residents.orEmpty()
)

fun List<LocationInfo>.toLocationDtoList() = map { it.toLocationDto() }

fun LocationEntity.toLocationDto() = LocationDto(
    id = id.orZero(),
    name = name,
    url = url,
    dimension = dimension,
    created = created,
    type = type,
    residents = residents
)

fun List<LocationEntity>.toFavoriteDtoList() = map { it.toLocationDto() }

fun LocationDto.toLocationEntity() = LocationEntity(
    id = id.orZero(),
    name = name,
    url = url.orEmpty(),
    dimension = dimension.orEmpty(),
    created = created.orEmpty(),
    type = type.orEmpty(),
    residents = residents
)