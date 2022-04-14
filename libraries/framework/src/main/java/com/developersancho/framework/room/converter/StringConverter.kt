package com.developersancho.framework.room.converter

import androidx.room.TypeConverter
import com.developersancho.framework.extension.fromJson
import com.developersancho.framework.extension.toJson

class StringConverter {
    @TypeConverter
    fun toListOfStrings(stringValue: String): List<String>? {
        return stringValue.fromJson()
    }

    @TypeConverter
    fun fromListOfStrings(listOfString: List<String>?): String {
        return listOfString.toJson()
    }
}