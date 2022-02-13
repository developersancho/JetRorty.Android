package com.developersancho.jetrorty.di

import com.developersancho.jetrorty.provider.ResourceProvider
import com.developersancho.jetrorty.provider.ResourceProviderImpl
import com.developersancho.jetrorty.provider.ThemeProvider
import com.developersancho.jetrorty.provider.ThemeProviderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val providerModule = module {
    single<ResourceProvider> { ResourceProviderImpl(androidContext()) }
    single<ThemeProvider> { ThemeProviderImpl(androidContext()) }
}