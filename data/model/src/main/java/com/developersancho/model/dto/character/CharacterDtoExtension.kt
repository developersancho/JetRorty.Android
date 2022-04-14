package com.developersancho.model.dto.character

import com.developersancho.framework.extension.orZero
import com.developersancho.model.local.character.CharacterEntity
import com.developersancho.model.local.character.CharacterLocationEntity
import com.developersancho.model.remote.base.Status
import com.developersancho.model.remote.character.CharacterInfo
import com.developersancho.model.remote.character.Location
import com.developersancho.model.remote.character.Origin

fun String.getIdFromUrl(): Int = substring(lastIndexOf("/") + 1).toIntOrNull() ?: 0

fun CharacterInfo.toCharacterDto() = CharacterDto(
    id = id.orZero(),
    name = name.orEmpty(),
    imageUrl = image,
    created = created,
    origin = origin?.toLocationDto(),
    location = location?.toLocationDto(),
    status = status ?: Status.Unknown,
    species = species,
    gender = gender,
    type = type,
    url = url,
    episodes = episodes.orEmpty()
)

fun List<CharacterInfo>.toCharacterDtoList() = map { it.toCharacterDto() }

fun CharacterEntity.toCharacterDto() = CharacterDto(
    id = id,
    name = name,
    imageUrl = imageUrl,
    created = created,
    origin = origin?.toLocationDto(),
    location = location?.toLocationDto(),
    status = status,
    species = species,
    gender = gender,
    type = type,
    url = url,
    episodes = episodes
)

fun List<CharacterEntity>.toFavoriteDtoList() = map { it.toCharacterDto() }

fun Location.toLocationDto() = CharacterLocationDto(
    locationId = url?.getIdFromUrl().orZero(),
    name = name.toString(),
    url = url.toString()
)

fun Origin.toLocationDto() = CharacterLocationDto(
    locationId = url?.getIdFromUrl().orZero(),
    name = name.toString(),
    url = url.toString()
)

fun CharacterLocationEntity.toLocationDto() = CharacterLocationDto(
    locationId = url.getIdFromUrl(),
    name = name,
    url = url
)

fun CharacterLocationDto.toLocationDto() = CharacterLocationEntity(
    locationId = url.getIdFromUrl().orZero(),
    name = name,
    url = url
)

fun CharacterDto.toCharacterEntity() = CharacterEntity(
    id = id.orZero(),
    name = name,
    imageUrl = imageUrl.orEmpty(),
    created = created.orEmpty(),
    origin = origin?.toLocationDto(),
    location = location?.toLocationDto(),
    status = status,
    species = species.orEmpty(),
    gender = gender.orEmpty(),
    type = type.orEmpty(),
    url = url.orEmpty(),
    episodes = episodes
)