@file:Suppress("UnstableApiUsage")

package com.developersancho.framework.platform

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.google.android.gms.common.GoogleApiAvailability
import com.huawei.hms.api.HuaweiApiAvailability
import timber.log.Timber
import java.lang.reflect.AccessibleObject
import java.lang.reflect.Field
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

private const val TAG = "MobileService"

enum class MobileServiceType {
    HMS, GMS, NON
}

data class EmuiVersion(
    val name: String,
    val code: Int
)

fun Context.isHMS(): Boolean {
    return MobileService.getMobileServiceType(this, MobileServiceType.GMS) == MobileServiceType.HMS
}

object MobileService {
    /**
     * Mobile services availability of devices
     *
     * @return Device mobile service type enum
     */
    fun getMobileServiceType(
        context: Context,
        firstPriority: MobileServiceType? = null
    ): MobileServiceType {
        val gms: Boolean = GoogleApiAvailability.getInstance()
            .isGooglePlayServicesAvailable(context) == com.google.android.gms.common.ConnectionResult.SUCCESS
        val hms: Boolean = HuaweiApiAvailability.getInstance()
            .isHuaweiMobileServicesAvailable(context) == com.huawei.hms.api.ConnectionResult.SUCCESS


        val app: ApplicationInfo = context.packageManager
            .getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
        val bundle = app.metaData

        return if (gms && hms) {
            firstPriority
                ?: when {
                    bundle.getString("mobile_service").isNullOrEmpty() -> {
                        MobileServiceType.GMS
                    }
                    bundle.getString("mobile_service") == "gms" -> {
                        MobileServiceType.GMS
                    }
                    else -> {
                        MobileServiceType.HMS
                    }
                }

        } else if (gms) {
            MobileServiceType.GMS
        } else if (hms) {
            MobileServiceType.HMS
        } else {
            MobileServiceType.NON
        }
    }

    /**
     * Emui information of Huawei Devices
     *
     * @return EmuiVersion object contains name and code fields name format is EmotionUI_X.X.X and code is integer
     */
    fun getDeviceEmuiVersion(): EmuiVersion {
        return EmuiVersion(getEmuiVersionName(), getEmuiVersionCode())
    }

    @SuppressLint("PrivateApi")
    private fun getEmuiVersionName(): String {
        val classType: Class<*>
        var emuiVerName = ""
        try {
            classType = Class.forName("android.os.SystemProperties")
            val getMethod: Method =
                classType.getDeclaredMethod("get", String::class.java)
            emuiVerName =
                getMethod.invoke(classType, "ro.build.version.emui") as String
        } catch (e: ClassNotFoundException) {
            Timber.tag(TAG).e("ClassNotFoundException")
        } catch (e: NoSuchMethodException) {
            Timber.tag(TAG).e("NoSuchMethodException")
        } catch (e: IllegalAccessException) {
            Timber.tag(TAG).e("IllegalAccessException")
        } catch (e: InvocationTargetException) {
            Timber.tag(TAG).e("InvocationTargetException")
        }
        Timber.tag(TAG).i("Emui version name: $emuiVerName")
        return emuiVerName
    }

    private fun getEmuiVersionCode(): Int {
        var returnObj: Any? = null
        var emuiVersionCode = 0
        try {
            val targetClass = Class.forName("com.huawei.android.os.BuildEx\$VERSION")
            val field: Field = targetClass.getDeclaredField("EMUI_SDK_INT")
            AccessibleObject.setAccessible(arrayOf(field), true)
            returnObj = field.get(targetClass)
            if (null != returnObj) {
                emuiVersionCode = (returnObj as Int?)!!
            }
        } catch (e: ClassNotFoundException) {
            Timber.tag(TAG).e("ClassNotFoundException: ")
        } catch (e: NoSuchFieldException) {
            Timber.tag(TAG).e("NoSuchFieldException: ")
        } catch (e: IllegalAccessException) {
            Timber.tag(TAG).e("IllegalAccessException: ")
        } catch (e: ClassCastException) {
            Timber.tag(TAG)
                .e("ClassCastException: getEMUIVersionCode is not a number $returnObj")
        }
        Timber.tag(TAG).i("emuiVersionCodeValue: %s", emuiVersionCode)
        return emuiVersionCode
    }
}