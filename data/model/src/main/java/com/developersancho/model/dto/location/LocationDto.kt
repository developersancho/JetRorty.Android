package com.developersancho.model.dto.location

import android.os.Parcelable
import com.developersancho.model.dto.character.CharacterDto
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationDto(
    val id: Int,
    val name: String,
    val url: String? = null,
    val dimension: String? = null,
    val created: String? = null,
    val type: String? = null,
    val residents: List<String> = emptyList(),
    val residentDtoList: MutableList<CharacterDto> = mutableListOf(),
    var isFavorite: Boolean = false
) : Parcelable {
    companion object {
        fun init() = LocationDto(
            id = 3,
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
    }
}