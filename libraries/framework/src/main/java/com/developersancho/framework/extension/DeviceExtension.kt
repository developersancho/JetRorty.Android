package com.developersancho.framework.extension

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi

fun isEmulator(): Boolean {
    return (Build.FINGERPRINT.startsWith("generic")
            || Build.FINGERPRINT.startsWith("unknown")
            || Build.MODEL.contains("google_sdk")
            || Build.MODEL.contains("Emulator")
            || Build.MODEL.contains("Android SDK built for x86")
            || Build.MANUFACTURER.contains("Genymotion")
            || Build.MODEL.startsWith("sdk_")
            || Build.DEVICE.startsWith("emulator")
            || Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")
            || "google_sdk" == Build.PRODUCT)
}

fun Context.developerEnabled(): Boolean {
    return Settings.Secure.getInt(
        this.contentResolver,
        Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0
    ) != 0
}

@SuppressLint("HardwareIds")
fun Context.deviceId(): String {
    val androidId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
    return androidId.orEmpty()
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