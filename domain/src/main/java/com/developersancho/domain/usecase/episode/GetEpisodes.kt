package com.developersancho.domain.usecase.episode

import androidx.annotation.VisibleForTesting
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.developersancho.framework.usecase.FlowPagingUseCase
import com.developersancho.model.dto.episode.EpisodeDto
import com.developersancho.repository.episode.EpisodeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEpisodes @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val repository: EpisodeRepository
) : FlowPagingUseCase<GetEpisodes.Params, EpisodeDto>() {

    data class Params(
        val pagingConfig: PagingConfig,
        val options: Map<String, String>
    )

    override fun execute(params: Params): Flow<PagingData<EpisodeDto>> {
        return Pager(
            config = params.pagingConfig,
            pagingSourceFactory = { EpisodePagingSource(repository, params.options) }
        ).flow
    }
}