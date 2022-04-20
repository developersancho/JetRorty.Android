package com.developersancho.domain.di

import com.developersancho.repository.character.CharacterRepository
import com.developersancho.repository.location.LocationRepository
import com.developersancho.testutils.MockkUnitTest
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class LocationDomainModuleTest : MockkUnitTest() {
    private lateinit var module: LocationDomainModule

    override fun onCreate() {
        super.onCreate()
        module = LocationDomainModule()
    }

    @Test
    fun verifyProvideGetLocations() {
        val repository = mockk<LocationRepository>()
        val getEpisodes = module.provideGetLocations(repository)

        Assert.assertEquals(repository, getEpisodes.repository)
    }

    @Test
    fun verifyProvideGetLocationDetail() {
        val charRepo = mockk<CharacterRepository>()
        val locationRepo = mockk<LocationRepository>()
        val getDetail = module.provideGetLocationDetail(locationRepo, charRepo)

        Assert.assertEquals(charRepo, getDetail.charRepo)
        Assert.assertEquals(locationRepo, getDetail.locRepo)
    }

    @Test
    fun verifyProvideGetFavorites() {
        val repo = mockk<LocationRepository>()
        val getFavoriteList = module.provideGetLocationFavorites(repo)

        Assert.assertEquals(repo, getFavoriteList.repository)
    }

    @Test
    fun verifyProvideAddFavorite() {
        val repo = mockk<LocationRepository>()
        val addFavorite = module.provideAddLocationFavorite(repo)

        Assert.assertEquals(repo, addFavorite.repository)
    }

    @Test
    fun verifyProvideDeleteFavorite() {
        val repo = mockk<LocationRepository>()
        val deleteFavorite = module.provideDeleteLocationFavorite(repo)

        Assert.assertEquals(repo, deleteFavorite.repository)
    }

    @Test
    fun verifyProvideUpdateFavorite() {
        val repo = mockk<LocationRepository>()
        val updateFavorite = module.provideUpdateLocationFavorite(repo)

        Assert.assertEquals(repo, updateFavorite.repository)
    }
}