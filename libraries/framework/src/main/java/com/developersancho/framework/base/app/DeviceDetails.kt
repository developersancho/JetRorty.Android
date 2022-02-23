/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.framework.base.app

import android.os.Build

data class DeviceDetails(
    val deviceId: String,
    val appVersionName: String,
    val osVersion: String = Build.VERSION.RELEASE,
    val manufacturer: String = Build.MANUFACTURER,
    val brand: String = Build.BRAND,
    val device: String = Build.DEVICE,
    val model: String = Build.MODEL
)