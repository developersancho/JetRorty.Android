/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.framework.base.app

import android.app.Application
import com.developersancho.framework.BuildConfig
import com.developersancho.framework.extension.appVersion
import com.developersancho.framework.extension.deviceId
import timber.log.Timber

open class CoreApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            val deviceId = deviceId()
            val version = appVersion()
            val deviceDetails = DeviceDetails(deviceId, version)
            val remoteTree = TimberRemoteTree(deviceDetails)

            Timber.plant(remoteTree)
        } else {
            // TODO plant timber release tree.
        }
    }
}