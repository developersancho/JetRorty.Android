package com.developersancho.domain.di

import android.annotation.SuppressLint
import com.developersancho.domain.usecase.language.GetLanguage
import com.developersancho.domain.usecase.language.SaveLanguage
import com.developersancho.repository.langauge.LanguageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@SuppressLint("VisibleForTests")
@Module
@InstallIn(SingletonComponent::class)
class LanguageModule {

    @Singleton
    @Provides
    fun provideSaveLanguage(repository: LanguageRepository): SaveLanguage {
        return SaveLanguage(repository)
    }

    @Singleton
    @Provides
    fun provideGetLanguage(repository: LanguageRepository): GetLanguage {
        return GetLanguage(repository)
    }
}