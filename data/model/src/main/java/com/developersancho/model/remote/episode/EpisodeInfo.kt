/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.model.remote.episode

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EpisodeInfo(
    @Json(name = "air_date") val airDate: String?,
    @Json(name = "characters") val characters: List<String>?,
    @Json(name = "created") val created: String?,
    @Json(name = "episode") val episode: String?,
    @Json(name = "id") val id: Int?,
    @Json(name = "name") val name: String?,
    @Json(name = "url") val url: String?
)