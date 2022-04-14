package com.developersancho.model.dto.character

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterLocationDto(
    val locationId: Int,
    val name: String,
    val url: String
) : Parcelable