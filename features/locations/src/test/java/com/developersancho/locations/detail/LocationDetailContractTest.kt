package com.developersancho.locations.detail

import com.developersancho.domain.mockdata.MockData
import org.junit.Assert
import org.junit.Test

class LocationDetailContractTest {
    private lateinit var event: LocationDetailEvent

    private lateinit var state: LocationDetailState

    @Test
    fun verifyEventLoadDetail() {
        val id = 1
        event = LocationDetailEvent.LoadDetail(id)

        val eventLoadDetail = event as LocationDetailEvent.LoadDetail
        Assert.assertEquals(id, eventLoadDetail.id)
    }

    @Test
    fun verifyState() {
        val dto = MockData.getLocationDto()
        state = LocationDetailState(dto)

        Assert.assertEquals(dto, state.location)
    }
}