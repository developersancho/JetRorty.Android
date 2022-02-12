package com.developersancho.jetrorty.di

import com.developersancho.jetrorty.features.characters.CharactersViewModel
import com.developersancho.jetrorty.features.detail.DetailViewModel
import com.developersancho.jetrorty.features.favorites.FavoritesViewModel
import com.developersancho.jetrorty.features.main.MainViewModel
import com.developersancho.jetrorty.features.settings.SettingsViewModel
import com.developersancho.jetrorty.features.splash.InitViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { InitViewModel() }
    viewModel { MainViewModel() }
    viewModel { CharactersViewModel(get(), get()) }
    viewModel { DetailViewModel(get(), get()) }
    viewModel { FavoritesViewModel(get(), get()) }
    viewModel { SettingsViewModel(get()) }
}