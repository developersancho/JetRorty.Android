package com.developersancho.jetrorty.app

import com.developersancho.framework.base.app.AppInitializer
import com.developersancho.framework.base.app.CoreApplication
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class JetRortyApp : CoreApplication() {

    @Inject
    lateinit var initializer: AppInitializer

    override fun onCreate() {
        super.onCreate()
        initializer.init(this)
    }
}