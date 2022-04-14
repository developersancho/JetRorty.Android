package com.developersancho.locations.list

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
import com.developersancho.framework.base.mvi.BaseViewState
import com.developersancho.framework.extension.cast
import com.developersancho.framework.extension.orZero
import com.developersancho.locations.list.view.LocationBottomSheetContent
import com.developersancho.locations.list.view.LocationContent
import com.developersancho.locations.list.view.LocationFavoriteContent
import com.developersancho.model.dto.location.LocationDto
import com.developersancho.provider.NavigationProvider
import com.developersancho.theme.*
import com.developersancho.theme.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun LocationsScreen(
    modifier: Modifier = Modifier,
    viewModel: LocationsViewModel = hiltViewModel(),
    navigator: NavigationProvider
) {
    val uiState by viewModel.uiState.collectAsState()

    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    val selectedFavorite = remember { mutableStateOf(LocationDto.init()) }

    LocationsBody(modifier = modifier, bottomSheetState = bottomSheetState, sheetContent = {
        LocationBottomSheetContent(
            location = selectedFavorite.value,
            onCancel = {
                coroutineScope.launch {
                    bottomSheetState.hide()
                }
            },
            onApprove = {
                coroutineScope.launch {
                    viewModel.onTriggerEvent(LocationsEvent.DeleteFavorite(selectedFavorite.value.id.orZero()))
                    bottomSheetState.hide()
                }
            }
        )
    }) { padding ->
        LocationTab(
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
private fun LocationTab(
    uiState: BaseViewState<*>,
    viewModel: LocationsViewModel,
    padding: PaddingValues,
    navigator: NavigationProvider,
    modifier: Modifier,
    coroutineScope: CoroutineScope,
    bottomSheetState: ModalBottomSheetState,
    selectedFavorite: MutableState<LocationDto>
) {
    Column {
        val tabsName = remember { LocationTabs.values().map { it.value } }
        val selectedIndex =
            rememberSaveable { mutableStateOf(LocationTabs.LOCATION_LIST.ordinal) }
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
                            LocationTabs.LOCATION_LIST.value -> {
                                selectedIndex.value = LocationTabs.LOCATION_LIST.ordinal
                            }
                            LocationTabs.FAVORITE_LIST.value -> {
                                selectedIndex.value = LocationTabs.FAVORITE_LIST.ordinal
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
            LocationTabs.LOCATION_LIST.ordinal -> {
                LocationsPage(uiState, viewModel, padding, navigator, modifier)
            }
            LocationTabs.FAVORITE_LIST.ordinal -> {
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

private enum class LocationTabs(@StringRes val value: Int) {
    LOCATION_LIST(R.string.text_location_list),
    FAVORITE_LIST(R.string.text_favorite_list);
}

@Composable
private fun LocationsPage(
    uiState: BaseViewState<*>,
    viewModel: LocationsViewModel,
    paddings: PaddingValues,
    navigator: NavigationProvider,
    modifier: Modifier
) {
    when (uiState) {
        is BaseViewState.Data -> LocationContent(
            viewModel = viewModel,
            paddingValues = paddings,
            viewState = uiState.cast<BaseViewState.Data<LocationsState>>().value,
            selectItem = { id -> navigator.openLocationDetail(id) }
        )
        is BaseViewState.Empty -> EmptyView(modifier = modifier)
        is BaseViewState.Error -> ErrorView(
            e = uiState.cast<BaseViewState.Error>().throwable,
            action = {
                viewModel.onTriggerEvent(LocationsEvent.LoadLocations)
            }
        )
        is BaseViewState.Loading -> LoadingView()
    }

    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(LocationsEvent.LoadLocations)
    })
}

@Composable
private fun FavoritesPage(
    coroutineScope: CoroutineScope,
    bottomSheetState: ModalBottomSheetState,
    uiState: BaseViewState<*>,
    viewModel: LocationsViewModel,
    navigator: NavigationProvider,
    modifier: Modifier,
    selectedFavorite: MutableState<LocationDto>
) {
    when (uiState) {
        is BaseViewState.Data -> LocationFavoriteContent(
            favors = uiState.cast<BaseViewState.Data<LocationsState>>().value.favorList,
            selectedFavorite = selectedFavorite,
            onDetailItem = { id -> navigator.openLocationDetail(id) },
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
                viewModel.onTriggerEvent(LocationsEvent.LoadFavorites)
            }
        )
        is BaseViewState.Loading -> LoadingView()
    }

    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(LocationsEvent.LoadFavorites)
    })
}

@Composable
private fun LocationsBody(
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
                topBar = { JRToolbar(R.string.toolbar_locations_title, elevation = 0.dp) },
                content = { pageContent.invoke(it) }
            )
        }
    )
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
fun LocationsScreenPreview() {
    JetRortyTheme {
        Surface {
        }
    }
}