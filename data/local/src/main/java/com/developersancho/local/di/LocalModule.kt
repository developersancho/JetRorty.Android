/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.local.di

import android.content.Context
import androidx.room.Room
import com.developersancho.local.BuildConfig
import com.developersancho.local.dao.FavoriteDao
import com.developersancho.local.db.RortyDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val DB_NAME = "db_name"

fun localModule() = module {
    single(named(DB_NAME)) { provideDatabaseName() }
    single { provideDatabase(get(named(DB_NAME)), androidContext()) }
    single { provideFavoriteDao(get()) }
}

fun provideDatabaseName(): String {
    return BuildConfig.DB_NAME
}

fun provideDatabase(
    dbname: String,
    context: Context
): RortyDatabase {
    return Room.databaseBuilder(context, RortyDatabase::class.java, dbname)
        .fallbackToDestructiveMigration()
        .build()
}

fun provideFavoriteDao(appDatabase: RortyDatabase): FavoriteDao {
    return appDatabase.favoriteDao()
}