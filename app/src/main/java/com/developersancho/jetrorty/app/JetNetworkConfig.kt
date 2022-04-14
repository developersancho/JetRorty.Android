package com.developersancho.jetrorty.app

import com.developersancho.framework.base.app.NetworkConfig
import com.developersancho.jetrorty.BuildConfig

class JetNetworkConfig : NetworkConfig() {
    override fun baseUrl(): String {
        return BuildConfig.BASE_URL
    }

    override fun timeOut(): Long {
        return 30L
    }

    override fun isDev(): Boolean {
        return BuildConfig.DEBUG
    }
}