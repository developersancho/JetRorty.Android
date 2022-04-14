package com.developersancho.domain.di

import android.annotation.SuppressLint
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@SuppressLint("VisibleForTests")
@Module(
    includes = [
        CharacterDomainModule::class,
        EpisodeDomainModule::class,
        LocationDomainModule::class,
        WelcomeModule::class,
        LanguageModule::class
    ]
)
@InstallIn(SingletonComponent::class)
class DomainModule