package com.developersancho.detail

import com.developersancho.model.dto.CharacterDto
import com.developersancho.model.dto.KeyValueModel

data class DetailViewState(
    val character: CharacterDto? = null,
    val list: List<KeyValueModel> = emptyList()
)

sealed class DetailEvent {
    data class LoadDetail(val id: Int) : DetailEvent()
}