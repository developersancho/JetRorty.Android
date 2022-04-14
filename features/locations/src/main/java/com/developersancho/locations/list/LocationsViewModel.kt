package com.developersancho.locations.list

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.developersancho.domain.usecase.location.GetLocations
import com.developersancho.domain.usecase.location.favorite.DeleteLocationFavorite
import com.developersancho.domain.usecase.location.favorite.GetLocationFavorites
import com.developersancho.domain.usecase.location.favorite.UpdateLocationFavorite
import com.developersancho.framework.base.mvi.BaseViewState
import com.developersancho.framework.base.mvi.MviViewModel
import com.developersancho.model.dto.location.LocationDto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val getLocations: GetLocations,
    private val updateLocationFavorite: UpdateLocationFavorite,
    private val getFavorites: GetLocationFavorites,
    private val deleteFavorite: DeleteLocationFavorite
) : MviViewModel<BaseViewState<LocationsState>, LocationsEvent>() {

    private val config = PagingConfig(pageSize = 20)

    override fun onTriggerEvent(eventType: LocationsEvent) {
        when (eventType) {
            is LocationsEvent.LoadLocations -> onLoadLocations()
            is LocationsEvent.AddOrRemoveFavorite -> onAddOrRemoveFavorite(eventType.location)
            is LocationsEvent.DeleteFavorite -> onDeleteFavorite(eventType.id)
            is LocationsEvent.LoadFavorites -> onLoadFavorites()
        }
    }

    private fun onLoadLocations() = safeLaunch {
        setState(BaseViewState.Loading)
        val params = GetLocations.Params(config, hashMapOf())
        val pagedFlow = getLocations(params).cachedIn(scope = viewModelScope)
        setState(BaseViewState.Data(LocationsState(pagedData = pagedFlow)))
    }

    private fun onAddOrRemoveFavorite(dto: LocationDto) = safeLaunch {
        val params = UpdateLocationFavorite.Params(dto)
        call(updateLocationFavorite(params))
    }

    private fun onLoadFavorites() = safeLaunch {
        call(getFavorites(Unit)) {
            if (it.isEmpty()) {
                setState(BaseViewState.Empty)
            } else {
                setState(BaseViewState.Data(LocationsState(favorList = it)))
            }
        }
    }

    private fun onDeleteFavorite(id: Int) = safeLaunch {
        call(deleteFavorite(DeleteLocationFavorite.Params(id))) {
            onTriggerEvent(LocationsEvent.LoadFavorites)
        }
    }
}