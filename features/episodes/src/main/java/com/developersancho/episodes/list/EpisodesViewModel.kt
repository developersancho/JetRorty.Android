package com.developersancho.episodes.list

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.developersancho.domain.usecase.episode.GetEpisodes
import com.developersancho.domain.usecase.episode.favorite.DeleteEpisodeFavorite
import com.developersancho.domain.usecase.episode.favorite.GetEpisodeFavorites
import com.developersancho.domain.usecase.episode.favorite.UpdateEpisodeFavorite
import com.developersancho.framework.base.mvi.BaseViewState
import com.developersancho.framework.base.mvi.MviViewModel
import com.developersancho.model.dto.episode.EpisodeDto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val getEpisodes: GetEpisodes,
    private val updateEpisodeFavorite: UpdateEpisodeFavorite,
    private val getFavorites: GetEpisodeFavorites,
    private val deleteFavorite: DeleteEpisodeFavorite
) : MviViewModel<BaseViewState<EpisodesState>, EpisodesEvent>() {

    private val config = PagingConfig(pageSize = 20)

    override fun onTriggerEvent(eventType: EpisodesEvent) {
        when (eventType) {
            is EpisodesEvent.LoadEpisodes -> onLoadEpisodes()
            is EpisodesEvent.AddOrRemoveFavorite -> onAddOrRemoveFavorite(eventType.episode)
            is EpisodesEvent.DeleteFavorite -> onDeleteFavorite(eventType.id)
            is EpisodesEvent.LoadFavorites -> onLoadFavorites()
        }
    }

    private fun onLoadEpisodes() = safeLaunch {
        setState(BaseViewState.Loading)
        val params = GetEpisodes.Params(config, hashMapOf())
        val pagedFlow = getEpisodes(params).cachedIn(scope = viewModelScope)
        setState(BaseViewState.Data(EpisodesState(pagedData = pagedFlow)))
    }

    private fun onAddOrRemoveFavorite(dto: EpisodeDto) = safeLaunch {
        val params = UpdateEpisodeFavorite.Params(dto)
        call(updateEpisodeFavorite(params))
    }

    private fun onLoadFavorites() = safeLaunch {
        call(getFavorites(Unit)) {
            if (it.isEmpty()) {
                setState(BaseViewState.Empty)
            } else {
                setState(BaseViewState.Data(EpisodesState(favorList = it)))
            }
        }
    }

    private fun onDeleteFavorite(id: Int) = safeLaunch {
        call(deleteFavorite(DeleteEpisodeFavorite.Params(id))) {
            onTriggerEvent(EpisodesEvent.LoadFavorites)
        }
    }
}