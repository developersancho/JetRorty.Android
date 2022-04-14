package com.developersancho.model.local.episode

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = EpisodeEntity.TABLE_NAME)
data class EpisodeEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = COLUMN_ID) val id: Int,
    @ColumnInfo(name = COLUMN_NAME) val name: String,
    @ColumnInfo(name = COLUMN_URL) val url: String,
    @ColumnInfo(name = COLUMN_AIR_DATE) val airDate: String,
    @ColumnInfo(name = COLUMN_CREATED) val created: String,
    @ColumnInfo(name = COLUMN_EPISODE) val episode: String,
    @ColumnInfo(name = COLUMN_CHARACTERS) val characters: List<String>
) {
    companion object {
        const val TABLE_NAME = "episode_favorite"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_URL = "url"
        const val COLUMN_AIR_DATE = "airDate"
        const val COLUMN_CREATED = "created"
        const val COLUMN_EPISODE = "episode"
        const val COLUMN_CHARACTERS = "characters"
    }
}