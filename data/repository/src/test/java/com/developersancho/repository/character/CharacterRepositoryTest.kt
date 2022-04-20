package com.developersancho.repository.character

import com.developersancho.local.dao.CharacterFavoriteDao
import com.developersancho.model.local.character.CharacterEntity
import com.developersancho.model.remote.base.Status
import com.developersancho.remote.service.CharacterService
import com.developersancho.repository.mockdata.RepositoryMockData
import com.developersancho.testutils.MockkUnitTest
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class CharacterRepositoryTest : MockkUnitTest() {

    @MockK(relaxed = true)
    lateinit var characterService: CharacterService

    @MockK(relaxed = true)
    lateinit var dao: CharacterFavoriteDao

    private lateinit var repository: CharacterRepository

    override fun onCreate() {
        super.onCreate()
        repository = CharacterRepository(characterService, dao)
    }

    @Test
    fun getCharacters() = runTest {
        // Given
        val characterPage = 1
        val characterOptions = hashMapOf<String, String>()

        val page = slot<Int>()
        val options = slot<Map<String, String>>()

        // When
        repository.getCharacterList(
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
        repository.getCharacterList(
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
        repository.getCharacter(
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
        repository.getFavoriteList()

        coVerify { dao.getFavoriteList() }
    }

    @Test
    fun getFavorite() = runTest {
        val characterIdToFind = 1
        val characterIdCaptor = slot<Int>()

        repository.getFavorite(characterIdToFind)

        coVerify { dao.getFavorite(capture(characterIdCaptor)) }

        assertEquals(characterIdToFind, characterIdCaptor.captured)
    }

    @Test
    fun deleteFavoriteById() = runTest {
        val characterIdToDelete = 1
        val characterIdCaptor = slot<Int>()
        repository.deleteFavoriteById(characterIdToDelete)

        coVerify {
            dao.deleteFavoriteById(capture(characterIdCaptor))
        }
        assertEquals(characterIdToDelete, characterIdCaptor.captured)
    }

    @Test
    fun saveFavorite() = runTest {
        val favoriteToInsert = CharacterEntity(
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
            episodes = emptyList()
        )

        val characterCaptor = slot<CharacterEntity>()
        repository.saveFavorite(favoriteToInsert)

        coVerify { dao.insert(capture(characterCaptor)) }
        assertEquals(favoriteToInsert, characterCaptor.captured)
    }

    @Test
    fun saveFavoriteList() = runTest {
        val favoritesToInsert = RepositoryMockData.getCharacterFavoriteList()
        val charactersInsertedCaptor = slot<List<CharacterEntity>>()
        repository.saveFavoriteList(favoritesToInsert)

        coVerify { dao.insert(capture(charactersInsertedCaptor)) }
        assertEquals(favoritesToInsert, charactersInsertedCaptor.captured)
    }
}