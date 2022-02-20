package com.developersancho.jetrorty.di

import com.developersancho.characters.CharactersViewModel
import com.developersancho.detail.DetailViewModel
import com.developersancho.favorites.FavoritesViewModel
import com.developersancho.settings.SettingsViewModel
import com.developersancho.splash.InitViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { InitViewModel() }
    viewModel { CharactersViewModel(get(), get()) }
    viewModel { DetailViewModel(get(), get()) }
    viewModel { FavoritesViewModel(get(), get()) }
    viewModel { SettingsViewModel(get()) }
}