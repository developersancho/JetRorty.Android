/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.model.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationDto(
    val locationId: Int,
    val name: String,
    val url: String
) : Parcelable