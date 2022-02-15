package com.developersancho.domain.mockdata

import com.developersancho.model.dto.CharacterDto
import com.developersancho.model.remote.base.PageInfo
import com.developersancho.model.remote.base.Status
import com.developersancho.model.remote.character.CharacterResponse

object MockData {

    fun getCharacterResponse(): CharacterResponse {
        return CharacterResponse(
            pageInfo = PageInfo(1, null, 20, null),
            results = listOf()
        )
    }

    fun getCharacterDto(): CharacterDto {
        return CharacterDto(
            id = 0, name = "3-D Man", image = "http://i.annihil.us/535fecbbb9784.jpg",
            created = "",
            origin = null,
            location = null,
            status = Status.Unknown,
            species = "",
            gender = "",
            type = "",
            url = "",
            episode = emptyList()
        )
    }

    fun getCharacterDtoList(): List<CharacterDto> {
        return listOf(
            CharacterDto(
                id = 0, name = "3-D Man", image = "http://i.annihil.us/535fecbbb9784.jpg",
                created = "",
                origin = null,
                location = null,
                status = Status.Unknown,
                species = "",
                gender = "",
                type = "",
                url = "",
                episode = emptyList()
            ),
            CharacterDto(
                id = 1, name = "A-Bomb (HAS)", image = "http://i.annihil.us/5232158de5b16.jpg",
                created = "",
                origin = null,
                location = null,
                status = Status.Unknown,
                species = "",
                gender = "",
                type = "",
                url = "",
                episode = emptyList()
            ),
            CharacterDto(
                id = 2, name = "A.I.M", image = "http://i.annihil.us/52602f21f29ec.jpg",
                created = "",
                origin = null,
                location = null,
                status = Status.Unknown,
                species = "",
                gender = "",
                type = "",
                url = "",
                episode = emptyList()
            )
        )
    }
}