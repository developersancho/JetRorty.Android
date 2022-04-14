package com.developersancho.domain.di

import android.annotation.SuppressLint
import com.developersancho.domain.usecase.character.GetCharacterDetail
import com.developersancho.domain.usecase.character.GetCharacters
import com.developersancho.domain.usecase.character.favorite.AddCharacterFavorite
import com.developersancho.domain.usecase.character.favorite.DeleteCharacterFavorite
import com.developersancho.domain.usecase.character.favorite.GetCharacterFavorites
import com.developersancho.domain.usecase.character.favorite.UpdateCharacterFavorite
import com.developersancho.repository.character.CharacterRepository
import com.developersancho.repository.episode.EpisodeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@SuppressLint("VisibleForTests")
@Module
@InstallIn(SingletonComponent::class)
class CharacterDomainModule {

    @Singleton
    @Provides
    fun provideGetCharacters(repository: CharacterRepository): GetCharacters {
        return GetCharacters(repository)
    }

    @Singleton
    @Provides
    fun provideGetCharacterDetail(
        repository: CharacterRepository,
        episodeRepository: EpisodeRepository
    ): GetCharacterDetail {
        return GetCharacterDetail(repository, episodeRepository)
    }

    @Singleton
    @Provides
    fun provideAddCharacterFavorite(repository: CharacterRepository): AddCharacterFavorite {
        return AddCharacterFavorite(repository)
    }

    @Singleton
    @Provides
    fun provideDeleteCharacterFavorite(repository: CharacterRepository): DeleteCharacterFavorite {
        return DeleteCharacterFavorite(repository)
    }

    @Singleton
    @Provides
    fun provideGetCharacterFavorites(repository: CharacterRepository): GetCharacterFavorites {
        return GetCharacterFavorites(repository)
    }

    @Singleton
    @Provides
    fun provideUpdateCharacterFavorite(repository: CharacterRepository): UpdateCharacterFavorite {
        return UpdateCharacterFavorite(repository)
    }
}