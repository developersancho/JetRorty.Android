/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.favorites.favorite

import com.developersancho.favorites.FavoritesEvent
import com.developersancho.favorites.FavoritesViewState
import com.developersancho.favorites.mockdata.FeatureMockData
import org.junit.Assert
import org.junit.Test

class FavoritesContractTest {

    private lateinit var event: FavoritesEvent

    private lateinit var state: FavoritesViewState

    @Test
    fun verifyEventLoadFavorite_ShouldSettledCorrectly() {
        event = FavoritesEvent.LoadFavorite

        val eventLoadFavorite = event as FavoritesEvent.LoadFavorite
        Assert.assertEquals(event, eventLoadFavorite)
    }

    @Test
    fun verifyEventDeleteItem_ShouldSettledCorrectly() {
        val itemId = 1
        event = FavoritesEvent.DeleteItem(itemId)

        val eventDeleteItem = event as FavoritesEvent.DeleteItem
        Assert.assertEquals(itemId, eventDeleteItem.id)
    }

    @Test
    fun verifyStateDetail_ShouldSettledCorrectly() {
        val list = FeatureMockData.getCharacterDtoList()
        state = FavoritesViewState(list)

        Assert.assertEquals(list, state.list)
    }
}