package com.developersancho.jetrorty.features.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.developersancho.framework.extension.launchActivity
import com.developersancho.jetrorty.features.main.MainActivity
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class InitActivity : AppCompatActivity() {

    private val viewModel by viewModel<InitViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { true }

        lifecycleScope.launchWhenCreated {
            viewModel.appIsReady.collectLatest {
                if (it) {
                    startMainActivity()
                    finish()
                }
            }
        }
    }

    private fun startMainActivity() {
        launchActivity<MainActivity> { }
    }
}