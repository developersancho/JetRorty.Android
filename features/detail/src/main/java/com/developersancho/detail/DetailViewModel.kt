/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.detail

import com.developersancho.domain.usecase.character.GetCharacterDetail
import com.developersancho.framework.base.BaseViewState
import com.developersancho.framework.base.mvi.MviViewModel
import com.developersancho.model.dto.CharacterDto
import com.developersancho.model.dto.KeyValueModel
import com.developersancho.provider.ResourceProvider
import com.developersancho.ui.resource.R

class DetailViewModel(
    private val getCharacterDetail: GetCharacterDetail,
    private val resourceProvider: ResourceProvider
) : MviViewModel<BaseViewState<DetailViewState>, DetailEvent>() {

    override fun onTriggerEvent(eventType: DetailEvent) {
        when (eventType) {
            is DetailEvent.LoadDetail -> loadDetail(eventType.id)
        }
    }

    private fun loadDetail(id: Int) = safeLaunch {
        val params = GetCharacterDetail.Params(detailId = id)
        execute(getCharacterDetail(params)) {
            setState(BaseViewState.Data(DetailViewState(character = it, list = getDetails(it))))
        }
    }

    private fun getDetails(character: CharacterDto): List<KeyValueModel> {
        val list = mutableListOf<KeyValueModel>()
        list.add(
            KeyValueModel(
                key = resourceProvider.getString(R.string.text_name),
                value = character.name
            )
        )
        list.add(
            KeyValueModel(
                key = resourceProvider.getString(R.string.text_species),
                value = character.species
            )
        )
        list.add(
            KeyValueModel(
                key = resourceProvider.getString(R.string.text_gender),
                value = character.gender
            )
        )
        list.add(
            KeyValueModel(
                key = resourceProvider.getString(R.string.text_last_know_location),
                value = character.origin?.name
            )
        )
        list.add(
            KeyValueModel(
                key = resourceProvider.getString(R.string.text_location),
                value = character.location?.name
            )
        )

        return list
    }
}