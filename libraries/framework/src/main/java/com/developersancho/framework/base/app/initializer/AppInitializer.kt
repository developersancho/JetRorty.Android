package com.developersancho.framework.base.app.initializer

import android.app.Application

interface AppInitializer {
    fun init(application: Application)
}