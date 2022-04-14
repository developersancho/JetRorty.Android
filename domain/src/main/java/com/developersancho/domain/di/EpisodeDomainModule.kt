package com.developersancho.domain.di

import android.annotation.SuppressLint
import com.developersancho.domain.usecase.episode.GetEpisodeDetail
import com.developersancho.domain.usecase.episode.GetEpisodes
import com.developersancho.domain.usecase.episode.favorite.AddEpisodeFavorite
import com.developersancho.domain.usecase.episode.favorite.DeleteEpisodeFavorite
import com.developersancho.domain.usecase.episode.favorite.GetEpisodeFavorites
import com.developersancho.domain.usecase.episode.favorite.UpdateEpisodeFavorite
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
class EpisodeDomainModule {

    @Singleton
    @Provides
    fun provideGetEpisodes(repository: EpisodeRepository): GetEpisodes {
        return GetEpisodes(repository)
    }

    @Singleton
    @Provides
    fun provideGetEpisodeDetail(
        episodeRepository: EpisodeRepository,
        characterRepository: CharacterRepository
    ): GetEpisodeDetail {
        return GetEpisodeDetail(episodeRepository, characterRepository)
    }

    @Singleton
    @Provides
    fun provideAddEpisodeFavorite(repository: EpisodeRepository): AddEpisodeFavorite {
        return AddEpisodeFavorite(repository)
    }

    @Singleton
    @Provides
    fun provideDeleteEpisodeFavorite(repository: EpisodeRepository): DeleteEpisodeFavorite {
        return DeleteEpisodeFavorite(repository)
    }

    @Singleton
    @Provides
    fun provideGetEpisodeFavorites(repository: EpisodeRepository): GetEpisodeFavorites {
        return GetEpisodeFavorites(repository)
    }

    @Singleton
    @Provides
    fun provideUpdateEpisodeFavorite(repository: EpisodeRepository): UpdateEpisodeFavorite {
        return UpdateEpisodeFavorite(repository)
    }
}