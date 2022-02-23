/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.model.remote.character

import com.developersancho.model.remote.base.PageInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterResponse(
    @Json(name = "info") val pageInfo: PageInfo?,
    @Json(name = "results") val results: List<CharacterInfo>?
)