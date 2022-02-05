package com.developersancho.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.developersancho.framework.room.dao.BaseDao
import com.developersancho.model.local.FavoriteEntity

@Dao
interface FavoriteDao : BaseDao<FavoriteEntity> {
    @Query("SELECT * FROM ${FavoriteEntity.TABLE_NAME}")
    suspend fun getFavoriteList(): List<FavoriteEntity>

    @Query("SELECT * FROM ${FavoriteEntity.TABLE_NAME} WHERE id = :favoriteId")
    suspend fun getFavorite(favoriteId: Int): FavoriteEntity?

    @Query("DELETE FROM ${FavoriteEntity.TABLE_NAME}")
    suspend fun deleteFavoriteList()

    @Query("DELETE FROM ${FavoriteEntity.TABLE_NAME} WHERE id = :favoriteId")
    suspend fun deleteFavoriteById(favoriteId: Int)
}
