/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.jetrorty.provider

import android.content.Context
import com.developersancho.provider.ResourceProvider

class ResourceProviderImpl(private val context: Context) : ResourceProvider {
    override fun getString(id: Int): String {
        return context.getString(id)
    }
}