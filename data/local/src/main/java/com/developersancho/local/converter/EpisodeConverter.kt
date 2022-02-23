/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.local.converter

import androidx.room.TypeConverter
import com.developersancho.framework.extension.fromJson
import com.developersancho.framework.extension.toJson

class EpisodeConverter {
    @TypeConverter
    fun toListOfStrings(stringValue: String): List<String>? {
        return stringValue.fromJson()
    }

    @TypeConverter
    fun fromListOfStrings(listOfString: List<String>?): String {
        return listOfString.toJson()
    }
}