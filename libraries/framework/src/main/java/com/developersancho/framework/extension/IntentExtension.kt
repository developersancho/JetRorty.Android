package com.developersancho.framework.extension

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment

inline fun <reified T : Any> Activity.launchActivity(
    requestCode: Int = -1,
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(this)
    intent.init()
    startActivityForResult(intent, requestCode, options)
}

inline fun <reified T : Any> Context.launchActivity(
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(this)
    intent.init()
    startActivity(intent, options)
}

inline fun <reified T : Any> newIntent(context: Context): Intent =
    Intent(context, T::class.java)

fun Activity.launchActivity(
    packageName: String,
    className: String,
    flags: Int = -1,
    bundle: Bundle? = null
) {
    val intent = Intent(Intent.ACTION_VIEW).setClassName(packageName, className)
    if (flags != -1) {
        intent.flags = flags
    }
    if (bundle != null) {
        intent.putExtras(bundle)
    }
    startActivity(intent)
}

fun Context.launchActivity(
    packageName: String,
    className: String,
    flags: Int = -1,
    bundle: Bundle? = null
) {
    val intent = Intent(Intent.ACTION_VIEW).setClassName(packageName, className)
    if (flags != -1) {
        intent.flags = flags
    }
    if (bundle != null) {
        intent.putExtras(bundle)
    }
    startActivity(intent)
}

/*
// Simple Activities
launchActivity<UserDetailActivity>()
// Add Intent extras
launchActivity<UserDetailActivity> {
    putExtra(INTENT_USER_ID, user.id)
}
// Add custom flags
launchActivity<UserDetailActivity> {
    putExtra(INTENT_USER_ID, user.id)
    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
}
// Add Shared Transistions
val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, avatar, "avatar")
launchActivity<UserDetailActivity>(options = options) {
    putExtra(INTENT_USER_ID, user.id)
}
// Add requestCode for startActivityForResult() call
launchActivity<UserDetailActivity>(requestCode = 1234) {
    putExtra(INTENT_USER_ID, user.id)
}
*/

fun Context.getActivity(): Activity? {
    if (this is ContextWrapper) {
        return this as? Activity
    }
    return null
}

@SuppressLint("QueryPermissionsNeeded")
fun Context.rateOnGooglePlay() {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse("market://details?id=${packageName}")
    packageManager?.let { packageManager ->
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}

@SuppressLint("QueryPermissionsNeeded")
fun Context.shareApplication() {
    val appPackageName = packageName

    val shareIntent = getActivity()?.let {
        ShareCompat.IntentBuilder.from(it)
            .setType("text/plain")
            .setText(
                "https://play.google.com/store/apps/details?id=$appPackageName"
            )
            .intent
    }
    if (shareIntent?.resolveActivity(packageManager) != null) {
        startActivity(shareIntent)
    }
}

fun Context.openAppOnPlayStore() = try {
    startActivity(
        Intent(
            Intent.ACTION_VIEW,
            Uri.parse("market://details?id=$packageName")
        )
    )
} catch (ex: ActivityNotFoundException) {
    startActivity(
        Intent(
            Intent.ACTION_VIEW,
            Uri.parse(
                ("https://play.google.com/store/apps/details?id=$packageName")
            )
        )
    )
}

fun Context.openAppOnAppGallery() {
    try {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("appmarket://details?id=$packageName")
            )
        )
    } catch (ex: ActivityNotFoundException) {
        val url = "https://appgallery.cloud.huawei.com/marketshare/app/C102867981"
        val url2 = "https://appgallery8.huawei.com/#/app/C102867981"
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(
                    ("https://appgallery8.huawei.com/#/app/C102867981")
                )
            )
        )
    }
}