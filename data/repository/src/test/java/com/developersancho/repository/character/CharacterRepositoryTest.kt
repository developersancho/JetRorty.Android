/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.repository.character

import com.developersancho.local.dao.FavoriteDao
import com.developersancho.model.local.FavoriteEntity
import com.developersancho.model.remote.base.Status
import com.developersancho.remote.service.CharacterService
import com.developersancho.testing.BaseMockTest
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class CharacterRepositoryTest : BaseMockTest() {

    @MockK(relaxed = true)
    lateinit var characterService: CharacterService
    private lateinit var characterRepository: CharacterRepository

    @MockK(relaxed = true)
    lateinit var favoriteDao: FavoriteDao

    private val fakeFavorite = listOf(
        FavoriteEntity(
            id = 0, name = "3-D Man", imageUrl = "http://i.annihil.us/535fecbbb9784.jpg",
            created = "",
            origin = null,
            location = null,
            status = Status.Unknown,
            species = "",
            gender = "",
            type = "",
            url = "",
            episode = emptyList()
        ),
        FavoriteEntity(
            id = 1, name = "A-Bomb (HAS)", imageUrl = "http://i.annihil.us/5232158de5b16.jpg",
            created = "",
            origin = null,
            location = null,
            status = Status.Unknown,
            species = "",
            gender = "",
            type = "",
            url = "",
            episode = emptyList()
        ),
        FavoriteEntity(
            id = 2, name = "A.I.M", imageUrl = "http://i.annihil.us/52602f21f29ec.jpg",
            created = "",
            origin = null,
            location = null,
            status = Status.Unknown,
            species = "",
            gender = "",
            type = "",
            url = "",
            episode = emptyList()
        )
    )

    override fun onCreate() {
        super.onCreate()
        characterRepository = CharacterRepository(characterService, favoriteDao)
    }

    @Test
    fun getCharacters() = runTest {
        // Given
        val characterPage = 1
        val characterOptions = hashMapOf<String, String>()

        val page = slot<Int>()
        val options = slot<Map<String, String>>()

        // When
        characterRepository.getCharacterList(
            page = characterPage, options = characterOptions
        )

        // Then
        coVerify {
            characterService.getCharacterList(
                page = capture(page),
                options = capture(options)
            )
        }

        assertEquals(characterPage, page.captured)
        assertEquals(characterOptions, options.captured)
    }

    @Test
    fun getCharactersByFilter() = runTest {
        // Given
        val characterPage = 1
        val characterOptions = hashMapOf(
            "name" to "rick",
            "status" to "alive"
        )

        val page = slot<Int>()
        val options = slot<Map<String, String>>()

        // When
        characterRepository.getCharacterList(
            page = characterPage, options = characterOptions
        )

        // Then
        coVerify {
            characterService.getCharacterList(
                page = capture(page),
                options = capture(options)
            )
        }

        assertEquals(characterPage, page.captured)
        assertEquals(characterOptions, options.captured)
    }

    @Test
    fun getCharacter() = runTest {
        // Given
        val characterId = 1
        val id = slot<Int>()

        // When
        characterRepository.getCharacter(
            characterId = characterId
        )

        // Then
        coVerify {
            characterService.getCharacter(
                characterId = capture(id)
            )
        }

        assertEquals(characterId, id.captured)
    }

    @Test
    fun getFavoriteList() = runTest {
        characterRepository.getFavoriteList()

        coVerify { favoriteDao.getFavoriteList() }
    }

    @Test
    fun getFavorite() = runTest {
        val characterIdToFind = 1
        val characterIdCaptor = slot<Int>()

        characterRepository.getFavorite(characterIdToFind)

        coVerify { favoriteDao.getFavorite(capture(characterIdCaptor)) }

        assertEquals(characterIdToFind, characterIdCaptor.captured)
    }

    @Test
    fun deleteFavoriteById() = runTest {
        val characterIdToDelete = 1
        val characterIdCaptor = slot<Int>()
        characterRepository.deleteFavoriteById(characterIdToDelete)

        coVerify {
            favoriteDao.deleteFavoriteById(capture(characterIdCaptor))
        }
        assertEquals(characterIdToDelete, characterIdCaptor.captured)
    }

    @Test
    fun saveFavorite() = runTest {
        val favoriteToInsert = FavoriteEntity(
            id = 0,
            name = "A.I.M",
            imageUrl = "http://i.annihil.us/535fecbbb9784.jpg",
            created = "",
            origin = null,
            location = null,
            status = Status.Unknown,
            species = "",
            gender = "",
            type = "",
            url = "",
            episode = emptyList()
        )

        val characterCaptor = slot<FavoriteEntity>()
        characterRepository.saveFavorite(favoriteToInsert)

        coVerify { favoriteDao.insert(capture(characterCaptor)) }
        assertEquals(favoriteToInsert, characterCaptor.captured)
    }

    @Test
    fun saveFavoriteList() = runTest {
        val favoritesToInsert = fakeFavorite
        val charactersInsertedCaptor = slot<List<FavoriteEntity>>()
        characterRepository.saveFavoriteList(favoritesToInsert)

        coVerify { favoriteDao.insert(capture(charactersInsertedCaptor)) }
        assertEquals(favoritesToInsert, charactersInsertedCaptor.captured)
    }
}