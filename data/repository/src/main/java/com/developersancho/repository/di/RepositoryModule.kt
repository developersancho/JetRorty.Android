package com.developersancho.repository.di

import android.annotation.SuppressLint
import com.developersancho.repository.character.CharacterRepository
import com.developersancho.repository.episode.EpisodeRepository
import com.developersancho.repository.location.LocationRepository
import org.koin.dsl.module

@SuppressLint("VisibleForTests")
val repositoryModule = module {
    single { CharacterRepository(get(), get()) }
    single { EpisodeRepository(get()) }
    single { LocationRepository(get()) }
}