/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.domain.di

import android.annotation.SuppressLint
import com.developersancho.domain.usecase.character.GetCharacterDetail
import com.developersancho.domain.usecase.character.GetCharacters
import com.developersancho.domain.usecase.favorite.AddFavorite
import com.developersancho.domain.usecase.favorite.DeleteFavorite
import com.developersancho.domain.usecase.favorite.GetFavorites
import com.developersancho.domain.usecase.favorite.UpdateFavorite
import org.koin.dsl.module

@SuppressLint("VisibleForTests")
val domainModule = module {
    single { GetCharacters(get()) }
    single { GetCharacterDetail(get()) }
    single { GetFavorites(get()) }
    single { AddFavorite(get()) }
    single { DeleteFavorite(get()) }
    single { UpdateFavorite(get()) }
}