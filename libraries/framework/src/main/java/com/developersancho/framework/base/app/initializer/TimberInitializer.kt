package com.developersancho.framework.base.app.initializer

import android.app.Application
import com.developersancho.framework.BuildConfig
import timber.log.Timber

class TimberInitializer : AppInitializer {
    override fun init(application: Application) {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}