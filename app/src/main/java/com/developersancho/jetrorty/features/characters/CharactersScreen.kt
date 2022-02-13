package com.developersancho.jetrorty.features.characters

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.developersancho.framework.base.BaseViewState
import com.developersancho.framework.extension.cast
import com.developersancho.jetrorty.features.characters.view.CharactersPageContentView
import com.developersancho.jetrorty.features.destinations.DetailScreenDestination
import com.developersancho.ui.resource.R
import com.developersancho.ui.theme.JetRortyTheme
import com.developersancho.ui.view.EmptyView
import com.developersancho.ui.view.ErrorView
import com.developersancho.ui.view.LoadingView
import com.developersancho.ui.view.RortyToolbar
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel

@Composable
fun CharactersScreen(
    modifier: Modifier = Modifier,
    viewModel: CharactersViewModel = getViewModel(),
    navigator: DestinationsNavigator
) {
    val uiState by viewModel.uiState.collectAsState()

    CharactersPage(modifier = modifier, pageContent = {
        when (uiState) {
            is BaseViewState.Data -> CharactersPageContentView(
                viewModel = viewModel,
                paddingValues = it,
                viewState = uiState.cast<BaseViewState.Data<CharactersViewState>>().value,
                selectItem = { id -> navigator.navigate(DetailScreenDestination(id = id)) }
            )
            is BaseViewState.Empty -> EmptyView(modifier = modifier)
            is BaseViewState.Error -> ErrorView(
                e = uiState.cast<BaseViewState.Error>().throwable,
                action = {
                    viewModel.onTriggerEvent(CharactersEvent.LoadCharacters)
                })
            is BaseViewState.Loading -> LoadingView()
        }
    })

    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(CharactersEvent.LoadCharacters)
    })
}


@Composable
private fun CharactersPage(
    modifier: Modifier = Modifier,
    pageContent: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = { RortyToolbar(R.string.toolbar_characters_title) },
        content = { pageContent.invoke(it) }
    )
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(
    showBackground = true, name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun CharacterScreenPreview() {
    JetRortyTheme {
        CharactersPage {}
    }
}