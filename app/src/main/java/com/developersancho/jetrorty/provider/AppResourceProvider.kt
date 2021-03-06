package com.developersancho.jetrorty.provider

import android.content.Context
import com.developersancho.provider.ResourceProvider

class AppResourceProvider(private val context: Context) : ResourceProvider {
    override fun getString(id: Int): String {
        return context.getString(id)
    }
}