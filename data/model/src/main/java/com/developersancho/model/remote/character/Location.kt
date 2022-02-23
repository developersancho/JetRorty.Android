/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.model.remote.character

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Location(
    @Json(name = "name") val name: String?,
    @Json(name = "url") val url: String?
)