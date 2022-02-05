package com.developersancho.jetrorty

import android.app.Application
import com.developersancho.jetrorty.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class JetRortyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        configureDi()
    }

    private fun configureDi() = startKoin {
        androidLogger()
        androidContext(this@JetRortyApp)
        modules(appModule)
    }

}