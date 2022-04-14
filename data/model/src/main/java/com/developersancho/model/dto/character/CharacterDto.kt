package com.developersancho.model.dto.character

import android.os.Parcelable
import com.developersancho.model.dto.episode.EpisodeDto
import com.developersancho.model.remote.base.Status
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterDto(
    val id: Int,
    val name: String,
    val imageUrl: String?,
    val created: String?,
    val origin: CharacterLocationDto?,
    val location: CharacterLocationDto?,
    val status: Status,
    val species: String?,
    val gender: String?,
    val type: String?,
    val url: String?,
    val episodes: List<String>,
    val episodeDtoList: MutableList<EpisodeDto> = mutableListOf(),
    var isFavorite: Boolean = false
) : Parcelable {
    companion object {
        fun init() = CharacterDto(
            id = 1,
            name = "Rick Sanchez",
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
            created = "2017-11-04T18:50:21.651Z",
            origin = CharacterLocationDto(
                locationId = 1,
                name = "Earth",
                url = "https://rickandmortyapi.com/api/location/1"
            ),
            location = CharacterLocationDto(
                locationId = 1,
                name = "Earth",
                url = "https://rickandmortyapi.com/api/location/20"
            ),
            status = Status.Alive,
            species = "Human",
            gender = "Male",
            type = null,
            url = "https://rickandmortyapi.com/api/character/2",
            episodes = listOf(
                "https://rickandmortyapi.com/api/episode/1",
                "https://rickandmortyapi.com/api/episode/2"
            ),
            isFavorite = false
        )
    }
}