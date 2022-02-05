package com.developersancho.model.dto

import com.developersancho.framework.extension.orZero
import com.developersancho.model.local.FavoriteEntity
import com.developersancho.model.local.LocationEntity
import com.developersancho.model.remote.base.Status
import com.developersancho.model.remote.character.CharacterInfo
import com.developersancho.model.remote.character.Location
import com.developersancho.model.remote.character.Origin

fun String.getIdFromUrl(): Int = substring(lastIndexOf("/") + 1).toIntOrNull() ?: 0

fun CharacterInfo.toCharacterDto() = CharacterDto(
    created,
    episode,
    gender,
    id,
    image,
    location?.toLocationDto(),
    name,
    origin?.toLocationDto(),
    species,
    status,
    type,
    url
)

fun List<CharacterInfo>.toCharacterDtoList() = map { it.toCharacterDto() }

fun FavoriteEntity.toCharacterDto() = CharacterDto(
    created,
    episode,
    gender,
    id,
    imageUrl,
    location?.toLocationDto(),
    name,
    origin?.toLocationDto(),
    species,
    status,
    type,
    url
)

fun List<FavoriteEntity>.toFavoriteDtoList() = map { it.toCharacterDto() }

fun Location.toLocationDto() = LocationDto(
    locationId = url?.getIdFromUrl().orZero(),
    name = name.toString(),
    url = url.toString()
)

fun Origin.toLocationDto() = LocationDto(
    locationId = url?.getIdFromUrl().orZero(),
    name = name.toString(),
    url = url.toString()
)

fun LocationEntity.toLocationDto() = LocationDto(
    locationId = url.getIdFromUrl(),
    name = name,
    url = url
)

fun LocationDto.toLocationDto() = LocationEntity(
    locationId = url.getIdFromUrl().orZero(),
    name = name,
    url = url
)

fun CharacterDto.toFavoriteEntity() = FavoriteEntity(
    id = id.orZero(),
    name = name.orEmpty(),
    imageUrl = image.orEmpty(),
    created = created.orEmpty(),
    origin = origin?.toLocationDto(),
    location = location?.toLocationDto(),
    status = status ?: Status.Unknown,
    species = species.orEmpty(),
    gender = gender.orEmpty(),
    type = type.orEmpty(),
    url = url.orEmpty(),
    episode = episode.orEmpty()
)