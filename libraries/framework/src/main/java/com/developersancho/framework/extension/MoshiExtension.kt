package com.developersancho.framework.extension

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

val moshi: Moshi = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()

inline fun <reified T> String.fromJson(): T? {
    return try {
        val jsonAdapter = moshi.adapter(T::class.java)
        jsonAdapter.fromJson(this)
    } catch (ex: Exception) {
        null
    }
}

inline fun <reified T> String.fromJsonList(): List<T>? {
    return try {
        val type = Types.newParameterizedType(MutableList::class.java, T::class.java)
        val jsonAdapter: JsonAdapter<List<T>> = moshi.adapter(type)
        jsonAdapter.fromJson(this)
    } catch (ex: Exception) {
        null
    }
}

inline fun <reified T> T.toJson(): String {
    return try {
        val jsonAdapter = moshi.adapter(T::class.java).serializeNulls().lenient()
        jsonAdapter.toJson(this)
    } catch (ex: Exception) {
        ""
    }
}