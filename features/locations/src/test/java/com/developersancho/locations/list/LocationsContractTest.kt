package com.developersancho.locations.list

import androidx.paging.PagingData
import com.developersancho.domain.mockdata.MockData
import com.developersancho.model.dto.location.LocationDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Test

class LocationsContractTest {
    private lateinit var event: LocationsEvent

    private lateinit var state: LocationsState

    @Test
    fun verifyEventLoadLocations() {
        event = LocationsEvent.LoadLocations

        val eventLoadDetail = event as LocationsEvent.LoadLocations
        Assert.assertEquals(event, eventLoadDetail)
    }

    @Test
    fun verifyEventAddOrRemoveFavorite() {
        val dto = MockData.getLocationDto()
        event = LocationsEvent.AddOrRemoveFavorite(dto)

        val eventAddOrRemoveFavorite = event as LocationsEvent.AddOrRemoveFavorite
        Assert.assertEquals(dto, eventAddOrRemoveFavorite.location)
    }

    @Test
    fun verifyEventLoadFavorites() {
        event = LocationsEvent.LoadFavorites

        val eventLoadFavorites = event as LocationsEvent.LoadFavorites
        Assert.assertEquals(event, eventLoadFavorites)
    }

    @Test
    fun verifyEventDeleteFavorite() {
        val id = 1
        event = LocationsEvent.DeleteFavorite(id)

        val eventDeleteFavorite = event as LocationsEvent.DeleteFavorite
        Assert.assertEquals(id, eventDeleteFavorite.id)
    }

    @Test
    fun verifyState() {
        val pagedData: Flow<PagingData<LocationDto>> =
            flow { emit(PagingData.empty()) }
        state = LocationsState(pagedData, emptyList())

        Assert.assertEquals(pagedData, state.pagedData)
        Assert.assertEquals(0, state.favorList.size)
    }
}