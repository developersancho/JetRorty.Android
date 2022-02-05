package com.developersancho.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.developersancho.framework.room.converter.StringConverter
import com.developersancho.local.converter.EpisodeConverter
import com.developersancho.local.dao.FavoriteDao
import com.developersancho.model.local.FavoriteEntity

@Database(
    entities = [FavoriteEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(EpisodeConverter::class)
abstract class RortyDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}
