package com.developersancho.model.dto.episode

import android.os.Parcelable
import com.developersancho.model.dto.character.CharacterDto
import kotlinx.parcelize.Parcelize

@Parcelize
data class EpisodeDto(
    val id: Int,
    val name: String,
    val url: String?,
    val airDate: String?,
    val created: String?,
    val episode: String?,
    val characters: List<String>,
    val characterDtoList: MutableList<CharacterDto> = mutableListOf(),
    var isFavorite: Boolean = false
) : Parcelable {
    companion object {
        fun init() = EpisodeDto(
            id = 28,
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
    }
}