package com.developersancho.jetrorty.feature.detail

import com.developersancho.jetrorty.features.detail.DetailEvent
import com.developersancho.jetrorty.features.detail.DetailViewState
import com.developersancho.model.dto.KeyValueModel
import com.developersancho.rorty.feature.mockdata.FeatureMockData
import org.junit.Assert
import org.junit.Test

class DetailContractTest {

    private lateinit var event: DetailEvent

    private lateinit var state: DetailViewState

    @Test
    fun verifyEventLoadDetail_ShouldSettledCorrectly() {
        val characterId = 1
        event = DetailEvent.LoadDetail(characterId)

        val eventLoadDetail = event as DetailEvent.LoadDetail
        Assert.assertEquals(characterId, eventLoadDetail.id)
    }

    @Test
    fun verifyStateCharacterDetail_ShouldSettledCorrectly() {
        val dto = FeatureMockData.getCharacterDto()
        state = DetailViewState(dto)

        Assert.assertEquals(dto, state.character)
    }

    @Test
    fun verifyStateDetail_ShouldSettledCorrectly() {
        val details = listOf(KeyValueModel(key = null, value = null))
        state = DetailViewState(list = details)

        Assert.assertEquals(details, state.list)
    }
}