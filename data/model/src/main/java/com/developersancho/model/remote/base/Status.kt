/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.model.remote.base

import com.developersancho.framework.network.moshi.IValueEnum
import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Json

@FallbackEnum(name = "unknown")
enum class Status(override val value: String) : IValueEnum {
    @Json(name = "Alive")
    Alive("Alive"),

    @Json(name = "Dead")
    Dead("Dead"),

    @Json(name = "unknown")
    Unknown("unknown");
}