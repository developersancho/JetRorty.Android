package com.developersancho.repository.mockdata

import com.developersancho.model.local.character.CharacterEntity
import com.developersancho.model.local.character.CharacterLocationEntity
import com.developersancho.model.local.episode.EpisodeEntity
import com.developersancho.model.local.location.LocationEntity
import com.developersancho.model.remote.base.Status

object RepositoryMockData {

    fun getCharacterFavoriteList(): List<CharacterEntity> {
        return listOf(
            CharacterEntity(
                id = 1,
                name = "Rick Sanchez",
                imageUrl = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
                created = "2017-11-04T18:50:21.651Z",
                origin = CharacterLocationEntity(
                    locationId = 1,
                    name = "Earth",
                    url = "https://rickandmortyapi.com/api/location/1"
                ),
                location = CharacterLocationEntity(
                    locationId = 1,
                    name = "Earth",
                    url = "https://rickandmortyapi.com/api/location/20"
                ),
                status = Status.Alive,
                species = "Human",
                gender = "Male",
                type = "",
                url = "https://rickandmortyapi.com/api/character/2",
                episodes = listOf(
                    "https://rickandmortyapi.com/api/episode/1",
                    "https://rickandmortyapi.com/api/episode/2"
                )
            )
        )
    }

    fun getEpisodeFavoriteList(): List<EpisodeEntity> {
        return listOf(
            EpisodeEntity(
                id = 1,
                name = "The Ricklantis Mixup",
                url = "https://rickandmortyapi.com/api/episode/28",
                airDate = "September 10, 2017",
                created = "2017-11-10T12:56:36.618Z",
                episode = "S03E07",
                characters = listOf(
                    "https://rickandmortyapi.com/api/character/1",
                    "https://rickandmortyapi.com/api/character/2"
                )
            ),
            EpisodeEntity(
                id = 2,
                name = "The Ricklantis Mixup",
                url = "https://rickandmortyapi.com/api/episode/28",
                airDate = "September 10, 2017",
                created = "2017-11-10T12:56:36.618Z",
                episode = "S03E07",
                characters = listOf(
                    "https://rickandmortyapi.com/api/character/1",
                    "https://rickandmortyapi.com/api/character/2"
                )
            )
        )
    }

    fun getLocationFavoriteList(): List<LocationEntity> {
        return listOf(
            LocationEntity(
                id = 1,
                name = "Citadel of Ricks",
                url = "https://rickandmortyapi.com/api/location/3",
                dimension = "unknown",
                created = "2017-11-10T13:08:13.191Z",
                type = "Space station",
                residents = listOf(
                    "https://rickandmortyapi.com/api/character/8",
                    "https://rickandmortyapi.com/api/character/14"
                )
            ),
            LocationEntity(
                id = 2,
                name = "Citadel of Ricks",
                url = "https://rickandmortyapi.com/api/location/3",
                dimension = "unknown",
                created = "2017-11-10T13:08:13.191Z",
                type = "Space station",
                residents = listOf(
                    "https://rickandmortyapi.com/api/character/8",
                    "https://rickandmortyapi.com/api/character/14"
                )
            )
        )
    }
}