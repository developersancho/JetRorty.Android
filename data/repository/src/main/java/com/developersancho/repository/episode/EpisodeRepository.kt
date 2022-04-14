package com.developersancho.repository.episode

import androidx.annotation.VisibleForTesting
import com.developersancho.local.dao.EpisodeFavoriteDao
import com.developersancho.model.local.episode.EpisodeEntity
import com.developersancho.remote.service.EpisodeService
import javax.inject.Inject

class EpisodeRepository
@Inject
constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    internal val service: EpisodeService,
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val dao: EpisodeFavoriteDao
) {
    suspend fun getEpisodeList(
        page: Int,
        options: Map<String, String>
    ) = service.getEpisodeList(page, options)

    suspend fun getEpisode(
        episodeId: Int
    ) = service.getEpisode(episodeId)

    suspend fun getEpisode(
        url: String
    ) = service.getEpisode(url)

    suspend fun getFavoriteList() = dao.getFavoriteList()

    suspend fun getFavorite(favoriteId: Int) = dao.getFavorite(favoriteId)

    suspend fun deleteFavoriteById(favoriteId: Int) = dao.deleteFavoriteById(favoriteId)

    suspend fun saveFavorite(entity: EpisodeEntity) = dao.insert(entity)

    suspend fun saveFavoriteList(entityList: List<EpisodeEntity>) = dao.insert(entityList)
}