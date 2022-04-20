package com.developersancho.domain.di

import com.developersancho.repository.character.CharacterRepository
import com.developersancho.repository.episode.EpisodeRepository
import com.developersancho.testutils.MockkUnitTest
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class EpisodeDomainModuleTest : MockkUnitTest() {
    private lateinit var module: EpisodeDomainModule

    override fun onCreate() {
        super.onCreate()
        module = EpisodeDomainModule()
    }

    @Test
    fun verifyProvideGetEpisodes() {
        val repository = mockk<EpisodeRepository>()
        val getEpisodes = module.provideGetEpisodes(repository)

        Assert.assertEquals(repository, getEpisodes.repository)
    }

    @Test
    fun verifyProvideGetEpisodeDetail() {
        val charRepo = mockk<CharacterRepository>()
        val episodeRepo = mockk<EpisodeRepository>()
        val getDetail = module.provideGetEpisodeDetail(episodeRepo, charRepo)

        Assert.assertEquals(charRepo, getDetail.charRepo)
        Assert.assertEquals(episodeRepo, getDetail.episodeRepo)
    }

    @Test
    fun verifyProvideGetFavorites() {
        val repo = mockk<EpisodeRepository>()
        val getFavoriteList = module.provideGetEpisodeFavorites(repo)

        Assert.assertEquals(repo, getFavoriteList.repository)
    }

    @Test
    fun verifyProvideAddFavorite() {
        val repo = mockk<EpisodeRepository>()
        val addFavorite = module.provideAddEpisodeFavorite(repo)

        Assert.assertEquals(repo, addFavorite.repository)
    }

    @Test
    fun verifyProvideDeleteFavorite() {
        val repo = mockk<EpisodeRepository>()
        val deleteFavorite = module.provideDeleteEpisodeFavorite(repo)

        Assert.assertEquals(repo, deleteFavorite.repository)
    }

    @Test
    fun verifyProvideUpdateFavorite() {
        val repo = mockk<EpisodeRepository>()
        val updateFavorite = module.provideUpdateEpisodeFavorite(repo)

        Assert.assertEquals(repo, updateFavorite.repository)
    }
}