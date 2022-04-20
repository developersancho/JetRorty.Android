package com.developersancho.domain.di

import android.annotation.SuppressLint
import com.developersancho.domain.usecase.location.GetLocationDetail
import com.developersancho.domain.usecase.location.GetLocations
import com.developersancho.domain.usecase.location.favorite.AddLocationFavorite
import com.developersancho.domain.usecase.location.favorite.DeleteLocationFavorite
import com.developersancho.domain.usecase.location.favorite.GetLocationFavorites
import com.developersancho.domain.usecase.location.favorite.UpdateLocationFavorite
import com.developersancho.repository.character.CharacterRepository
import com.developersancho.repository.location.LocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@SuppressLint("VisibleForTests")
@Module
@InstallIn(SingletonComponent::class)
class LocationDomainModule {

    @Singleton
    @Provides
    fun provideGetLocations(repository: LocationRepository): GetLocations {
        return GetLocations(repository)
    }

    @Singleton
    @Provides
    fun provideGetLocationDetail(
        locRepo: LocationRepository,
        charRepo: CharacterRepository
    ): GetLocationDetail {
        return GetLocationDetail(locRepo, charRepo)
    }

    @Singleton
    @Provides
    fun provideAddLocationFavorite(repository: LocationRepository): AddLocationFavorite {
        return AddLocationFavorite(repository)
    }

    @Singleton
    @Provides
    fun provideDeleteLocationFavorite(repository: LocationRepository): DeleteLocationFavorite {
        return DeleteLocationFavorite(repository)
    }

    @Singleton
    @Provides
    fun provideGetLocationFavorites(repository: LocationRepository): GetLocationFavorites {
        return GetLocationFavorites(repository)
    }

    @Singleton
    @Provides
    fun provideUpdateLocationFavorite(repository: LocationRepository): UpdateLocationFavorite {
        return UpdateLocationFavorite(repository)
    }
}