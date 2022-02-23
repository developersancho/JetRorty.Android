/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.model.remote.episode

import com.developersancho.model.remote.base.PageInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EpisodeResponse(
    @Json(name = "info") val pageInfo: PageInfo?,
    @Json(name = "results") val results: List<EpisodeInfo>?
)