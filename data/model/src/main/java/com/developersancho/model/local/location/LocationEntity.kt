package com.developersancho.model.local.location

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = LocationEntity.TABLE_NAME)
data class LocationEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = COLUMN_ID) val id: Int,
    @ColumnInfo(name = COLUMN_NAME) val name: String,
    @ColumnInfo(name = COLUMN_URL) val url: String,
    @ColumnInfo(name = COLUMN_DIMENSION) val dimension: String,
    @ColumnInfo(name = COLUMN_CREATED) val created: String,
    @ColumnInfo(name = COLUMN_TYPE) val type: String,
    @ColumnInfo(name = COLUMN_RESIDENTS) val residents: List<String>
) {
    companion object {
        const val TABLE_NAME = "location_favorite"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_URL = "url"
        const val COLUMN_DIMENSION = "dimension"
        const val COLUMN_CREATED = "created"
        const val COLUMN_TYPE = "type"
        const val COLUMN_RESIDENTS = "residents"
    }
}