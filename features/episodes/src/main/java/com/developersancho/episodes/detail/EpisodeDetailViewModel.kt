package com.developersancho.episodes.detail

import com.developersancho.domain.usecase.episode.GetEpisodeDetail
import com.developersancho.framework.base.mvi.BaseViewState
import com.developersancho.framework.base.mvi.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailViewModel @Inject constructor(
    private val getEpisodeDetail: GetEpisodeDetail
) : MviViewModel<BaseViewState<EpisodeDetailState>, EpisodeDetailEvent>() {

    override fun onTriggerEvent(eventType: EpisodeDetailEvent) {
        when (eventType) {
            is EpisodeDetailEvent.LoadDetail -> onLoadDetail(eventType.id)
        }
    }

    private fun onLoadDetail(id: Int) = safeLaunch {
        val params = GetEpisodeDetail.Params(detailId = id)
        execute(getEpisodeDetail(params)) { dto ->
            setState(BaseViewState.Data(EpisodeDetailState(dto)))
        }
    }
}