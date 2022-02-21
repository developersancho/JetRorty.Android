package com.developersancho.framework.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle

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