package com.developersancho.domain.mockdata

import com.developersancho.model.dto.character.CharacterDto
import com.developersancho.model.dto.episode.EpisodeDto
import com.developersancho.model.dto.location.LocationDto
import com.developersancho.model.remote.base.PageInfo
import com.developersancho.model.remote.base.Status
import com.developersancho.model.remote.character.CharacterResponse
import com.developersancho.model.remote.episode.EpisodeResponse
import com.developersancho.model.remote.location.LocationResponse

object MockData {

    fun getCharacterResponse(): CharacterResponse {
        return CharacterResponse(
            pageInfo = PageInfo(1, null, 20, null),
            results = listOf()
        )
    }

    fun getCharacterDto(): CharacterDto {
        return CharacterDto(
            id = 0, name = "3-D Man", imageUrl = "http://i.annihil.us/535fecbbb9784.jpg",
            created = "",
            origin = null,
            location = null,
            status = Status.Unknown,
            species = "",
            gender = "",
            type = "",
            url = "",
            episodes = emptyList()
        )
    }

    fun getCharacterDtoList(): List<CharacterDto> {
        return listOf(
            CharacterDto(
                id = 0, name = "3-D Man", imageUrl = "http://i.annihil.us/535fecbbb9784.jpg",
                created = "",
                origin = null,
                location = null,
                status = Status.Unknown,
                species = "",
                gender = "",
                type = "",
                url = "",
                episodes = emptyList()
            ),
            CharacterDto(
                id = 1, name = "A-Bomb (HAS)", imageUrl = "http://i.annihil.us/5232158de5b16.jpg",
                created = "",
                origin = null,
                location = null,
                status = Status.Unknown,
                species = "",
                gender = "",
                type = "",
                url = "",
                episodes = emptyList()
            ),
            CharacterDto(
                id = 2, name = "A.I.M", imageUrl = "http://i.annihil.us/52602f21f29ec.jpg",
                created = "",
                origin = null,
                location = null,
                status = Status.Unknown,
                species = "",
                gender = "",
                type = "",
                url = "",
                episodes = emptyList()
            )
        )
    }

    fun getEpisodeResponse(): EpisodeResponse {
        return EpisodeResponse(
            pageInfo = PageInfo(1, null, 20, null),
            results = listOf()
        )
    }

    fun getEpisodeDto(): EpisodeDto {
        return EpisodeDto.init()
    }

    fun getEpisodeDtoList(): List<EpisodeDto> {
        return listOf(
            EpisodeDto.init()
        )
    }

    fun getLocationResponse(): LocationResponse {
        return LocationResponse(
            pageInfo = PageInfo(1, null, 20, null),
            results = listOf()
        )
    }

    fun getLocationDto(): LocationDto {
        return LocationDto.init()
    }

    fun getLocationDtoList(): List<LocationDto> {
        return listOf(
            LocationDto.init()
        )
    }
}