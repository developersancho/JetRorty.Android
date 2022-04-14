package com.developersancho.episodes.list

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.developersancho.component.widget.*
import com.developersancho.episodes.list.view.EpisodeBottomSheetContent
import com.developersancho.episodes.list.view.EpisodeContent
import com.developersancho.episodes.list.view.EpisodeFavoriteContent
import com.developersancho.framework.base.mvi.BaseViewState
import com.developersancho.framework.extension.cast
import com.developersancho.framework.extension.orZero
import com.developersancho.model.dto.episode.EpisodeDto
import com.developersancho.provider.NavigationProvider
import com.developersancho.theme.*
import com.developersancho.theme.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun EpisodesScreen(
    modifier: Modifier = Modifier,
    viewModel: EpisodesViewModel = hiltViewModel(),
    navigator: NavigationProvider
) {
    val uiState by viewModel.uiState.collectAsState()

    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    val selectedFavorite = remember { mutableStateOf(EpisodeDto.init()) }

    EpisodesBody(modifier = modifier, bottomSheetState = bottomSheetState, sheetContent = {
        EpisodeBottomSheetContent(
            episode = selectedFavorite.value,
            onCancel = {
                coroutineScope.launch {
                    bottomSheetState.hide()
                }
            },
            onApprove = {
                coroutineScope.launch {
                    viewModel.onTriggerEvent(EpisodesEvent.DeleteFavorite(selectedFavorite.value.id.orZero()))
                    bottomSheetState.hide()
                }
            }
        )
    }) { padding ->
        EpisodeTab(
            uiState,
            viewModel,
            padding,
            navigator,
            modifier,
            coroutineScope,
            bottomSheetState,
            selectedFavorite
        )
    }
}

@Composable
private fun EpisodeTab(
    uiState: BaseViewState<*>,
    viewModel: EpisodesViewModel,
    padding: PaddingValues,
    navigator: NavigationProvider,
    modifier: Modifier,
    coroutineScope: CoroutineScope,
    bottomSheetState: ModalBottomSheetState,
    selectedFavorite: MutableState<EpisodeDto>
) {
    Column {
        val tabsName = remember { EpisodeTabs.values().map { it.value } }
        val selectedIndex =
            rememberSaveable { mutableStateOf(EpisodeTabs.EPISODE_LIST.ordinal) }
        TabRow(
            selectedTabIndex = selectedIndex.value,
            backgroundColor = JetRortyColors.primary,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    color = RedDark,
                    height = TabRowDefaults.IndicatorHeight,
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
                            EpisodeTabs.EPISODE_LIST.value -> {
                                selectedIndex.value = EpisodeTabs.EPISODE_LIST.ordinal
                            }
                            EpisodeTabs.FAVORITE_LIST.value -> {
                                selectedIndex.value = EpisodeTabs.FAVORITE_LIST.ordinal
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
            EpisodeTabs.EPISODE_LIST.ordinal -> {
                EpisodesPage(uiState, viewModel, padding, navigator, modifier)
            }
            EpisodeTabs.FAVORITE_LIST.ordinal -> {
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

private enum class EpisodeTabs(@StringRes val value: Int) {
    EPISODE_LIST(R.string.text_episode_list),
    FAVORITE_LIST(R.string.text_favorite_list);
}

@Composable
private fun EpisodesPage(
    uiState: BaseViewState<*>,
    viewModel: EpisodesViewModel,
    paddings: PaddingValues,
    navigator: NavigationProvider,
    modifier: Modifier
) {
    when (uiState) {
        is BaseViewState.Data -> EpisodeContent(
            viewModel = viewModel,
            paddingValues = paddings,
            viewState = uiState.cast<BaseViewState.Data<EpisodesState>>().value,
            selectItem = { id -> navigator.openEpisodeDetail(id) }
        )
        is BaseViewState.Empty -> EmptyView(modifier = modifier)
        is BaseViewState.Error -> ErrorView(
            e = uiState.cast<BaseViewState.Error>().throwable,
            action = {
                viewModel.onTriggerEvent(EpisodesEvent.LoadEpisodes)
            }
        )
        is BaseViewState.Loading -> LoadingView()
    }

    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(EpisodesEvent.LoadEpisodes)
    })
}

@Composable
private fun FavoritesPage(
    coroutineScope: CoroutineScope,
    bottomSheetState: ModalBottomSheetState,
    uiState: BaseViewState<*>,
    viewModel: EpisodesViewModel,
    navigator: NavigationProvider,
    modifier: Modifier,
    selectedFavorite: MutableState<EpisodeDto>
) {
    when (uiState) {
        is BaseViewState.Data -> EpisodeFavoriteContent(
            favors = uiState.cast<BaseViewState.Data<EpisodesState>>().value.favorList,
            selectedFavorite = selectedFavorite,
            onDetailItem = { id -> navigator.openEpisodeDetail(id) },
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
                viewModel.onTriggerEvent(EpisodesEvent.LoadFavorites)
            }
        )
        is BaseViewState.Loading -> LoadingView()
    }

    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(EpisodesEvent.LoadFavorites)
    })
}

@Composable
private fun EpisodesBody(
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
                topBar = { JRToolbar(R.string.toolbar_episodes_title, elevation = 0.dp) },
                content = { pageContent.invoke(it) }
            )
        }
    )
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
fun EpisodesScreenPreview() {
    JetRortyTheme {
        Surface(color = JetRortyColors.background) {
            // EpisodesBody {}
        }
    }
}