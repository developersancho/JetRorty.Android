/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.jetrorty.di

import com.developersancho.jetrorty.provider.ResourceProviderImpl
import com.developersancho.jetrorty.provider.ThemeProviderImpl
import com.developersancho.provider.ResourceProvider
import com.developersancho.provider.ThemeProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val providerModule = module {
    single<ResourceProvider> { ResourceProviderImpl(androidContext()) }
    single<ThemeProvider> { ThemeProviderImpl(androidContext()) }
}