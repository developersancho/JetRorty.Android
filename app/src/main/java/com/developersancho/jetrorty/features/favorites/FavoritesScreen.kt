package com.developersancho.jetrorty.features.favorites

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.developersancho.framework.base.BaseViewState
import com.developersancho.framework.extension.cast
import com.developersancho.jetrorty.features.destinations.DetailScreenDestination
import com.developersancho.jetrorty.features.favorites.view.FavoritesPageContentView
import com.developersancho.model.dto.CharacterDto
import com.developersancho.ui.resource.R
import com.developersancho.ui.theme.JetRortyTheme
import com.developersancho.ui.view.EmptyView
import com.developersancho.ui.view.ErrorView
import com.developersancho.ui.view.LoadingView
import com.developersancho.ui.view.RortyToolbar
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel

@Composable
fun FavoritesScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoritesViewModel = getViewModel(),
    navigator: DestinationsNavigator
) {
    val uiState by viewModel.uiState.collectAsState()

    val selectedFavorite = remember { mutableStateOf(CharacterDto.init()) }

    FavoritesPage(modifier = modifier, pageContent = {
        when (uiState) {
            is BaseViewState.Data -> FavoritesPageContentView(
                favors = uiState.cast<BaseViewState.Data<FavoritesViewState>>().value.list,
                selectedFavorite = selectedFavorite,
                onDetailItem = { id -> navigator.navigate(DetailScreenDestination(id = id)) },
                onDeleteItem = {}
            )
            is BaseViewState.Empty -> EmptyView(modifier = modifier)
            is BaseViewState.Error -> ErrorView(
                modifier = modifier,
                e = uiState.cast<BaseViewState.Error>().throwable,
                action = {
                    viewModel.onTriggerEvent(FavoritesEvent.LoadFavorite)
                })
            is BaseViewState.Loading -> LoadingView()
        }
    })

    SideEffect {
        viewModel.onTriggerEvent(FavoritesEvent.LoadFavorite)
    }
}

@Composable
private fun FavoritesPage(
    modifier: Modifier = Modifier,
    pageContent: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = { RortyToolbar(R.string.toolbar_favorites_title) },
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
        FavoritesPage {}
    }
}