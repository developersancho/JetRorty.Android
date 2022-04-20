package com.developersancho.domain.di

import com.developersancho.repository.character.CharacterRepository
import com.developersancho.repository.episode.EpisodeRepository
import com.developersancho.testutils.MockkUnitTest
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class CharacterDomainModuleTest : MockkUnitTest() {

    private lateinit var module: CharacterDomainModule

    override fun onCreate() {
        super.onCreate()
        module = CharacterDomainModule()
    }

    @Test
    fun verifyProvideGetCharacters() {
        val repository = mockk<CharacterRepository>()
        val getCharacterList = module.provideGetCharacters(repository)

        Assert.assertEquals(repository, getCharacterList.repository)
    }

    @Test
    fun verifyProvideGetCharacterDetail() {
        val charRepo = mockk<CharacterRepository>()
        val episodeRepo = mockk<EpisodeRepository>()
        val getCharacterDetail = module.provideGetCharacterDetail(charRepo, episodeRepo)

        Assert.assertEquals(charRepo, getCharacterDetail.charRepo)
        Assert.assertEquals(episodeRepo, getCharacterDetail.episodeRepo)
    }

    @Test
    fun verifyProvideGetFavorites() {
        val charRepo = mockk<CharacterRepository>()
        val getFavoriteList = module.provideGetCharacterFavorites(charRepo)

        Assert.assertEquals(charRepo, getFavoriteList.repository)
    }

    @Test
    fun verifyProvideAddFavorite() {
        val charRepo = mockk<CharacterRepository>()
        val addFavorite = module.provideAddCharacterFavorite(charRepo)

        Assert.assertEquals(charRepo, addFavorite.repository)
    }

    @Test
    fun verifyProvideDeleteFavorite() {
        val charRepo = mockk<CharacterRepository>()
        val deleteFavorite = module.provideDeleteCharacterFavorite(charRepo)

        Assert.assertEquals(charRepo, deleteFavorite.repository)
    }

    @Test
    fun verifyProvideUpdateFavorite() {
        val charRepo = mockk<CharacterRepository>()
        val updateFavorite = module.provideUpdateCharacterFavorite(charRepo)

        Assert.assertEquals(charRepo, updateFavorite.repository)
    }
}