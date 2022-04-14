package com.developersancho.settings

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.developersancho.component.widget.JRToolbar
import com.developersancho.provider.NavigationProvider
import com.developersancho.settings.view.SettingsContent
import com.developersancho.theme.JetRortyTheme
import com.developersancho.theme.R
import com.ramcosta.composedestinations.annotation.Destination

@Destination(start = true)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel(),
    navigator: NavigationProvider
) {
    val checkedState = remember { mutableStateOf(viewModel.isNightMode()) }

    SettingsBody(modifier) {
        SettingsContent(viewModel, checkedState, navigator)
    }
}

@Composable
private fun SettingsBody(
    modifier: Modifier = Modifier,
    pageContent: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = { JRToolbar(R.string.toolbar_settings_title) },
        content = { pageContent.invoke(it) }
    )
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
fun SettingsScreenPreview() {
    JetRortyTheme {
        Surface {
            SettingsBody {
            }
        }
    }
}