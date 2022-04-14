package com.developersancho.jetframework

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.analytics.FirebaseAnalytics

data class User(
    val userType: String
)

@SuppressLint("MissingPermission")
@Composable
fun rememberAnalytics(user: User): FirebaseAnalytics {
    val context = LocalContext.current
    val analytics: FirebaseAnalytics = remember {
        FirebaseAnalytics.getInstance(context)
    }

    // On every successful composition, update FirebaseAnalytics with
    // the userType from the current User, ensuring that future analytics
    // events have this metadata attached
    SideEffect {
        analytics.setUserProperty("userType", user.userType)
    }
    return analytics
}