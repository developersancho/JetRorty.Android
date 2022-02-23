/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.favorites

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.developersancho.favorites.view.FavoritesPageContentView
import com.developersancho.framework.base.BaseViewState
import com.developersancho.framework.extension.cast
import com.developersancho.framework.extension.orZero
import com.developersancho.model.dto.CharacterDto
import com.developersancho.provider.NavigationProvider
import com.developersancho.ui.resource.R
import com.developersancho.ui.theme.JetRortyTheme
import com.developersancho.ui.view.EmptyView
import com.developersancho.ui.view.ErrorView
import com.developersancho.ui.view.LoadingView
import com.developersancho.ui.view.RortyToolbar
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun FavoritesPage(
    modifier: Modifier = Modifier,
    viewModel: FavoritesViewModel = getViewModel(),
    navigator: NavigationProvider
) {
    val uiState by viewModel.uiState.collectAsState()
    val selectedFavorite = remember { mutableStateOf(CharacterDto.init()) }
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()

    FavoritesPage(
        modifier = modifier,
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            FavoriteBottomSheetScreen(
                character = selectedFavorite.value,
                onCancel = {
                    coroutineScope.launch {
                        bottomSheetScaffoldState.bottomSheetState.collapse()
                    }
                },
                onApprove = {
                    coroutineScope.launch {
                        viewModel.onTriggerEvent(FavoritesEvent.DeleteItem(selectedFavorite.value.id.orZero()))
                        bottomSheetScaffoldState.bottomSheetState.collapse()
                    }
                }
            )
        },
        pageContent = {
            when (uiState) {
                is BaseViewState.Data -> FavoritesPageContentView(
                    favors = uiState.cast<BaseViewState.Data<FavoritesViewState>>().value.list,
                    selectedFavorite = selectedFavorite,
                    onDetailItem = { id -> navigator.openDetail(id = id) },
                    onDeleteItem = {
                        coroutineScope.launch {
                            if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                                bottomSheetScaffoldState.bottomSheetState.expand()
                            } else {
                                bottomSheetScaffoldState.bottomSheetState.collapse()
                            }
                        }
                    }
                )
                is BaseViewState.Empty -> EmptyView(modifier = modifier)
                is BaseViewState.Error -> ErrorView(
                    modifier = modifier,
                    e = uiState.cast<BaseViewState.Error>().throwable,
                    action = {
                        viewModel.onTriggerEvent(FavoritesEvent.LoadFavorite)
                    }
                )
                is BaseViewState.Loading -> LoadingView()
            }
        }
    )

    SideEffect {
        viewModel.onTriggerEvent(FavoritesEvent.LoadFavorite)
    }
}

@Composable
private fun FavoritesPage(
    modifier: Modifier = Modifier,
    scaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
    sheetContent: @Composable ColumnScope.() -> Unit,
    pageContent: @Composable (PaddingValues) -> Unit
) {
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colors.background),
        sheetPeekHeight = 0.dp,
        sheetShape = RectangleShape,
        sheetContentColor = MaterialTheme.colors.background,
        topBar = { RortyToolbar(R.string.toolbar_favorites_title) },
        sheetContent = sheetContent,
        content = { pageContent.invoke(it) }
    )
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(
    showBackground = true, name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun FavoritesScreenPreview() {
    JetRortyTheme {
    }
}