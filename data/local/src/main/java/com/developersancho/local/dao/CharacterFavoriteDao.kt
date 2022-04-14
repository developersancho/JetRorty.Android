package com.developersancho.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.developersancho.framework.room.dao.BaseDao
import com.developersancho.model.local.character.CharacterEntity

@Dao
interface CharacterFavoriteDao : BaseDao<CharacterEntity> {
    @Query("SELECT * FROM ${CharacterEntity.TABLE_NAME}")
    suspend fun getFavoriteList(): List<CharacterEntity>

    @Query("SELECT * FROM ${CharacterEntity.TABLE_NAME} WHERE id = :favoriteId")
    suspend fun getFavorite(favoriteId: Int): CharacterEntity?

    @Query("DELETE FROM ${CharacterEntity.TABLE_NAME}")
    suspend fun deleteFavoriteList()

    @Query("DELETE FROM ${CharacterEntity.TABLE_NAME} WHERE id = :favoriteId")
    suspend fun deleteFavoriteById(favoriteId: Int)
}