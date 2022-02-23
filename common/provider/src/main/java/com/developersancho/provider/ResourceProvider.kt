/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.provider

import androidx.annotation.StringRes

interface ResourceProvider {
    fun getString(@StringRes id: Int): String
}