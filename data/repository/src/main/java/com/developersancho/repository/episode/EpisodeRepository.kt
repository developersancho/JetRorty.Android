/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.repository.episode

import androidx.annotation.VisibleForTesting
import com.developersancho.remote.service.EpisodeService

class EpisodeRepository(
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    internal val service: EpisodeService
) {
    suspend fun getEpisodeList(
        page: Int,
        options: Map<String, String>
    ) = service.getEpisodeList(page, options)

    suspend fun getEpisode(
        episodeId: Int
    ) = service.getEpisode(episodeId)
}