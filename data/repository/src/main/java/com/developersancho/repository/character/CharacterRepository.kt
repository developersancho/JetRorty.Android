package com.developersancho.repository.character

import androidx.annotation.VisibleForTesting
import com.developersancho.local.dao.FavoriteDao
import com.developersancho.model.local.FavoriteEntity
import com.developersancho.remote.service.CharacterService

class CharacterRepository(
    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val service: CharacterService,
    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val dao: FavoriteDao
) {
    suspend fun getCharacterList(
        page: Int,
        options: Map<String, String>
    ) = service.getCharacterList(page, options)

    suspend fun getCharacter(
        characterId: Int
    ) = service.getCharacter(characterId)

    suspend fun getFavoriteList() = dao.getFavoriteList()

    suspend fun getFavorite(favoriteId: Int) = dao.getFavorite(favoriteId)

    suspend fun deleteFavoriteById(favoriteId: Int) = dao.deleteFavoriteById(favoriteId)

    suspend fun saveFavorite(entity: FavoriteEntity) = dao.insert(entity)

    suspend fun saveFavoriteList(entityList: List<FavoriteEntity>) = dao.insert(entityList)
}
