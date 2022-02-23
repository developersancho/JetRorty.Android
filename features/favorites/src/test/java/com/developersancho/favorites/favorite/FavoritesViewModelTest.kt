/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.favorites.favorite

import app.cash.turbine.test
import com.developersancho.domain.usecase.favorite.DeleteFavorite
import com.developersancho.domain.usecase.favorite.GetFavorites
import com.developersancho.favorites.FavoritesEvent
import com.developersancho.favorites.FavoritesViewModel
import com.developersancho.framework.base.BaseViewState
import com.developersancho.model.dto.CharacterDto
import com.developersancho.testing.BaseMockTest
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

class FavoritesViewModelTest : BaseMockTest() {
    @RelaxedMockK
    lateinit var getFavorites: GetFavorites

    @RelaxedMockK
    lateinit var deleteFavorite: DeleteFavorite

    @SpyK
    @InjectMockKs
    lateinit var viewModel: FavoritesViewModel

    @Test
    fun verifyOnTriggerEventLoadFavorite() = runTest {
        // Arrange (Given)

        // Act (When)
        viewModel.onTriggerEvent(FavoritesEvent.LoadFavorite)

        // Assert (Then)
        coVerify { getFavorites.invoke(Unit) }
    }

    @Test
    fun verifyOnTriggerEventDeleteItem() = runTest {
        // Arrange (Given)
        val itemId = 1

        // Act (When)
        viewModel.onTriggerEvent(FavoritesEvent.DeleteItem(itemId))

        // Assert (Then)
        coVerify { deleteFavorite.invoke(DeleteFavorite.Params(itemId)) }
    }

    @Test
    fun verifyOnTriggerEventLoadFavorite_CheckState() = runTest {
        // Arrange (Given)
        val dtoList = mockk<List<CharacterDto>>()
        coEvery { getFavorites.invoke(Unit) } returns flow { emit(dtoList) }

        // Act (When)
        viewModel.onTriggerEvent(FavoritesEvent.LoadFavorite)

        // Assert (Then)
        viewModel.uiState.test {
            awaitItem().apply {
                Truth.assertThat(this).isNotNull()
                Truth.assertThat(this).isInstanceOf(BaseViewState::class.java)
            }
        }
    }

    @Test
    fun verifyOnTriggerEventDeleteItem_CheckState() = runTest {
        // Arrange (Given)
        val characterId = 1
        coEvery { deleteFavorite.invoke(DeleteFavorite.Params(characterId)) } returns flow { emit(Unit) }

        // Act (When)
        viewModel.onTriggerEvent(FavoritesEvent.DeleteItem(characterId))

        // Assert (Then)
        verify { viewModel.onTriggerEvent(FavoritesEvent.LoadFavorite) }
    }
}