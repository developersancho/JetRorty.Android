package com.developersancho.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.developersancho.local.converter.StringListConverter
import com.developersancho.local.dao.CharacterFavoriteDao
import com.developersancho.local.dao.EpisodeFavoriteDao
import com.developersancho.local.dao.LocationFavoriteDao
import com.developersancho.model.local.character.CharacterEntity
import com.developersancho.model.local.episode.EpisodeEntity
import com.developersancho.model.local.location.LocationEntity

@Database(
    entities = [CharacterEntity::class, EpisodeEntity::class, LocationEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(StringListConverter::class)
abstract class RortyDatabase : RoomDatabase() {
    abstract fun characterFavoriteDao(): CharacterFavoriteDao
    abstract fun episodeFavoriteDao(): EpisodeFavoriteDao
    abstract fun locationFavoriteDao(): LocationFavoriteDao
}