/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.framework.extension

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import java.io.File
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

@SuppressLint("MissingPermission", "NewApi")
fun Context.isInternetAvailable(): Boolean {
    var result = false

    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    cm.run {
        cm.getNetworkCapabilities(cm.activeNetwork)?.run {
            result = when {
                hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }
    }

    return result
}

fun isInternetAvailable(): Boolean {
    return try {
        val timeoutMs = 1500
        val sock = Socket()
        val sockaddr = InetSocketAddress("8.8.8.8", 53)

        sock.connect(sockaddr, timeoutMs)
        sock.close()

        true
    } catch (e: IOException) {
        false
    }
}

@SuppressLint("HardwareIds")
fun Context.isEmulator(): Boolean {
    val androidId = Settings.Secure.getString(this.contentResolver, "android_id")
    return Build.PRODUCT.contains("sdk") ||
        Build.HARDWARE.contains("goldfish") ||
        Build.HARDWARE.contains("ranchu") ||
        androidId == null
}

fun Context.isRooted(): Boolean {
    val isEmulator: Boolean = isEmulator()
    val buildTags = Build.TAGS
    return if (!isEmulator && buildTags != null && buildTags.contains("test-keys")) {
        true
    } else {
        var file = File("/system/app/Superuser.apk")
        if (file.exists()) {
            true
        } else {
            file = File("/system/xbin/su")
            !isEmulator && file.exists()
        }
    }
}

fun Context.appVersion(): String {
    return try {
        packageManager.getPackageInfo(packageName, 0).versionName
    } catch (ex: PackageManager.NameNotFoundException) {
        ""
    }
}

@RequiresApi(Build.VERSION_CODES.P)
fun Context.appVersionCode(): Long {
    return try {
        packageManager.getPackageInfo(packageName, 0).longVersionCode
    } catch (ex: PackageManager.NameNotFoundException) {
        0L
    }
}

fun Context.targetPlatform() = "Android"

@SuppressLint("HardwareIds")
fun Context.deviceId(): String {
    val androidId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
    return if (androidId.isNullOrEmpty()) "" else androidId
}