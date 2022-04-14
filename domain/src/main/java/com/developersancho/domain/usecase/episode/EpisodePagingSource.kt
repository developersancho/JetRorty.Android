package com.developersancho.domain.usecase.episode

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.developersancho.framework.extension.orZero
import com.developersancho.model.dto.episode.EpisodeDto
import com.developersancho.model.dto.episode.toEpisodeDtoList
import com.developersancho.repository.episode.EpisodeRepository
import java.io.IOException

class EpisodePagingSource(
    internal val repository: EpisodeRepository,
    internal val options: Map<String, String>
) : PagingSource<Int, EpisodeDto>() {
    override fun getRefreshKey(state: PagingState<Int, EpisodeDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EpisodeDto> {
        val page = params.key ?: 1
        return try {
            val response = repository.getEpisodeList(page, options)
            val episodeList = response.results.orEmpty().toEpisodeDtoList()

            episodeList.map {
                val episodeFav = repository.getFavorite(it.id.orZero())
                it.isFavorite = episodeFav != null
            }

            LoadResult.Page(
                data = episodeList,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (episodeList.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }
}