package com.developersancho.framework.base.app

class AppInitializerImpl(private vararg val initializers: AppInitializer) : AppInitializer {
    override fun init(application: CoreApplication) {
        initializers.forEach {
            it.init(application)
        }
    }
}