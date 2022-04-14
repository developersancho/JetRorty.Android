package com.developersancho.characters.list

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.IndicatorHeight
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.developersancho.characters.list.view.CharacterContent
import com.developersancho.characters.list.view.CharacterFavoriteContent
import com.developersancho.characters.list.view.FavoriteBottomSheetContent
import com.developersancho.component.widget.*
import com.developersancho.framework.base.mvi.BaseViewState
import com.developersancho.framework.extension.cast
import com.developersancho.framework.extension.orZero
import com.developersancho.model.dto.character.CharacterDto
import com.developersancho.provider.NavigationProvider
import com.developersancho.theme.*
import com.developersancho.theme.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun CharactersScreen(
    modifier: Modifier = Modifier,
    viewModel: CharactersViewModel = hiltViewModel(),
    bottomSheetState: ModalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden),
    navigator: NavigationProvider
) {
    val uiState by viewModel.uiState.collectAsState()

    val coroutineScope = rememberCoroutineScope()
    val selectedFavorite = remember { mutableStateOf(CharacterDto.init()) }

    CharactersBody(
        modifier, bottomSheetState,
        sheetContent = {
            FavoriteBottomSheetContent(
                character = selectedFavorite.value,
                onCancel = {
                    coroutineScope.launch {
                        bottomSheetState.hide()
                    }
                },
                onApprove = {
                    coroutineScope.launch {
                        viewModel.onTriggerEvent(CharactersEvent.DeleteFavorite(selectedFavorite.value.id.orZero()))
                        bottomSheetState.hide()
                    }
                }
            )
        }
    ) { padding ->
        Column {
            val tabsName = remember { CharacterTabs.values().map { it.value } }
            val selectedIndex = rememberSaveable { mutableStateOf(CharacterTabs.LIST.ordinal) }
            TabRow(
                selectedTabIndex = selectedIndex.value,
                backgroundColor = JetRortyColors.primary,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        color = RedDark,
                        height = IndicatorHeight,
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[selectedIndex.value])
                    )
                }
            ) {
                tabsName.forEachIndexed { index, stringResourceId ->
                    Tab(
                        selected = index == selectedIndex.value,
                        onClick = {
                            when (stringResourceId) {
                                CharacterTabs.LIST.value -> {
                                    selectedIndex.value = CharacterTabs.LIST.ordinal
                                }
                                CharacterTabs.FAVORITE.value -> {
                                    selectedIndex.value = CharacterTabs.FAVORITE.ordinal
                                }
                            }
                        },
                        text = {
                            Text(
                                text = stringResource(id = stringResourceId),
                                style = JetRortyTypography.h4
                            )
                        }
                    )
                }
            }
            when (selectedIndex.value) {
                CharacterTabs.LIST.ordinal -> {
                    CharactersPage(uiState, viewModel, padding, navigator, modifier)
                }
                CharacterTabs.FAVORITE.ordinal -> {
                    FavoritesPage(
                        coroutineScope,
                        bottomSheetState,
                        uiState,
                        viewModel,
                        navigator,
                        modifier,
                        selectedFavorite
                    )
                }
            }
        }
    }
}

private enum class CharacterTabs(@StringRes val value: Int) {
    LIST(R.string.text_character_list),
    FAVORITE(R.string.text_favorite_list);
}

@Composable
private fun CharactersPage(
    uiState: BaseViewState<*>,
    viewModel: CharactersViewModel,
    paddings: PaddingValues,
    navigator: NavigationProvider,
    modifier: Modifier
) {
    when (uiState) {
        is BaseViewState.Data -> CharacterContent(
            viewModel = viewModel,
            paddingValues = paddings,
            viewState = uiState.cast<BaseViewState.Data<CharactersViewState>>().value,
            selectItem = { id -> navigator.openCharacterDetail(id) }
        )
        is BaseViewState.Empty -> EmptyView(modifier = modifier)
        is BaseViewState.Error -> ErrorView(
            e = uiState.cast<BaseViewState.Error>().throwable,
            action = {
                viewModel.onTriggerEvent(CharactersEvent.LoadCharacters)
            }
        )
        is BaseViewState.Loading -> LoadingView()
    }

    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(CharactersEvent.LoadCharacters)
    })
}

@Composable
private fun FavoritesPage(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    bottomSheetState: ModalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden),
    uiState: BaseViewState<*>,
    viewModel: CharactersViewModel,
    navigator: NavigationProvider,
    modifier: Modifier,
    selectedFavorite: MutableState<CharacterDto>
) {
    when (uiState) {
        is BaseViewState.Data -> CharacterFavoriteContent(
            favors = uiState.cast<BaseViewState.Data<CharactersViewState>>().value.favorList,
            selectedFavorite = selectedFavorite,
            onDetailItem = { id -> navigator.openCharacterDetail(id) },
            onDeleteItem = {
                coroutineScope.launch {
                    if (bottomSheetState.isVisible) {
                        bottomSheetState.hide()
                    } else {
                        bottomSheetState.show()
                    }
                }
            }
        )
        is BaseViewState.Empty -> LottieEmptyView(modifier = modifier)
        is BaseViewState.Error -> LottieErrorView(
            modifier = modifier,
            e = uiState.cast<BaseViewState.Error>().throwable,
            action = {
                viewModel.onTriggerEvent(CharactersEvent.LoadFavorites)
            }
        )
        is BaseViewState.Loading -> LoadingView()
    }

    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(CharactersEvent.LoadFavorites)
    })
}

@Composable
private fun CharactersBody(
    modifier: Modifier = Modifier,
    bottomSheetState: ModalBottomSheetState,
    sheetContent: @Composable ColumnScope.() -> Unit,
    pageContent: @Composable (PaddingValues) -> Unit
) {
    ModalBottomSheetLayout(
        sheetContent = sheetContent,
        modifier = modifier
            .fillMaxSize(),
        sheetState = bottomSheetState,
        sheetContentColor = JetRortyColors.background,
        sheetShape = RectangleShape,
        content = {
            Scaffold(
                topBar = { JRToolbar(R.string.toolbar_characters_title, elevation = 0.dp) },
                content = { pageContent.invoke(it) }
            )
        }
    )
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
fun CharactersScreenPreview() {
    JetRortyTheme {
        Surface {
//            CharactersBody() {
//
//            }
        }
    }
}