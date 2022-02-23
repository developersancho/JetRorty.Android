/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.favorites.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.developersancho.framework.extension.orZero
import com.developersancho.model.dto.CharacterDto
import com.developersancho.model.remote.base.Status
import com.developersancho.ui.theme.JetRortyTheme

@Composable
fun FavoritesPageContentView(
    favors: List<CharacterDto>,
    selectedFavorite: MutableState<CharacterDto>,
    onDetailItem: (Int) -> Unit = {},
    onDeleteItem: (Int) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 4.dp),
    ) {
        itemsIndexed(favors, key = { _, item -> item.id.orZero() }) { _, favor ->
            FavoriteRow(
                dto = favor,
                onDetailClick = {
                    onDetailItem.invoke(favor.id.orZero())
                },
                onDeleteClick = {
                    selectedFavorite.value = favor
                    onDeleteItem.invoke(favor.id.orZero())
                }
            )
        }
    }
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
fun FavoritesPageContentViewPreview() {
    JetRortyTheme {
        val selectedFavor = remember {
            mutableStateOf(CharacterDto.init())
        }
        FavoritesPageContentView(
            favors = listOf(
                CharacterDto(
                    id = 1,
                    name = "Rick Sanchez",
                    image = "",
                    species = "Human",
                    status = Status.Unknown,
                    created = null,
                    episode = null,
                    gender = null,
                    location = null,
                    origin = null,
                    type = null,
                    url = null
                ),
                CharacterDto(
                    id = 2,
                    name = "Fit Sanchez",
                    image = "",
                    species = "Human",
                    status = Status.Dead,
                    created = null,
                    episode = null,
                    gender = null,
                    location = null,
                    origin = null,
                    type = null,
                    url = null
                ),
                CharacterDto(
                    id = 1,
                    name = "Pablo Sanchez",
                    image = "",
                    species = "Human",
                    status = Status.Alive,
                    created = null,
                    episode = null,
                    gender = null,
                    location = null,
                    origin = null,
                    type = null,
                    url = null
                )
            ),
            selectedFavorite = selectedFavor
        )
    }
}