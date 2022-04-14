package com.developersancho.splash

import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.developersancho.framework.extension.launchActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class StartActivity : FragmentActivity() {

    private val viewModel by viewModels<StartViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val splashScreen = installSplashScreen()
            splashScreen.setKeepOnScreenCondition { true }
        }
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenCreated {
            viewModel.startWelcome.collectLatest {
                delay(3000)
                if (it) navigateWelcomeActivity() else navigateMainActivity()
            }
        }
    }

    private fun navigateMainActivity() {
        launchActivity(
            packageName = packageName,
            className = "com.developersancho.jetrorty.navigation.MainActivity"
        )
    }

    private fun navigateWelcomeActivity() {
        launchActivity(
            packageName = packageName,
            className = "com.developersancho.welcome.WelcomeActivity"
        )
    }
}