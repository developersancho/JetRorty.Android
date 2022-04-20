package com.developersancho.repository.di

import com.developersancho.local.dao.CharacterFavoriteDao
import com.developersancho.local.dao.EpisodeFavoriteDao
import com.developersancho.local.dao.LocationFavoriteDao
import com.developersancho.remote.service.CharacterService
import com.developersancho.remote.service.EpisodeService
import com.developersancho.remote.service.LocationService
import com.developersancho.testutils.MockkUnitTest
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class RepositoryModuleTest : MockkUnitTest() {

    private lateinit var repositoryModule: RepositoryModule

    override fun onCreate() {
        super.onCreate()
        repositoryModule = RepositoryModule()
    }

    @Test
    fun verifyProvideCharacterRepository() {
        val service = mockk<CharacterService>()
        val dao = mockk<CharacterFavoriteDao>()
        val repository = repositoryModule.provideCharacterRepository(service, dao)

        Assert.assertEquals(service, repository.service)
        Assert.assertEquals(dao, repository.dao)
    }

    @Test
    fun verifyProvideEpisodeRepository() {
        val service = mockk<EpisodeService>()
        val dao = mockk<EpisodeFavoriteDao>()
        val repository = repositoryModule.provideEpisodeRepository(service, dao)

        Assert.assertEquals(service, repository.service)
        Assert.assertEquals(dao, repository.dao)
    }

    @Test
    fun verifyProvideLocationRepository() {
        val service = mockk<LocationService>()
        val dao = mockk<LocationFavoriteDao>()
        val repository = repositoryModule.provideLocationRepository(service, dao)

        Assert.assertEquals(service, repository.service)
        Assert.assertEquals(dao, repository.dao)
    }
}