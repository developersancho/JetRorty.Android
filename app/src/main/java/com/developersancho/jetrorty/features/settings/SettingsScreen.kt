package com.developersancho.jetrorty.features.settings
//
//import android.content.res.Configuration
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.material.Scaffold
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import com.developersancho.jetrorty.features.settings.view.SettingsPageContentView
//import com.developersancho.ui.resource.R
//import com.developersancho.ui.theme.JetRortyTheme
//import com.developersancho.ui.view.RortyToolbar
//import com.ramcosta.composedestinations.navigation.DestinationsNavigator
//import org.koin.androidx.compose.getViewModel
//
//@Composable
//fun SettingsScreen(
//    modifier: Modifier = Modifier,
//    viewModel: SettingsViewModel = getViewModel(),
//    navigator: DestinationsNavigator
//) {
//    val checkedState = remember { mutableStateOf(viewModel.isNightMode()) }
//
//    SettingsPage(modifier) {
//        SettingsPageContentView(checkedState, viewModel)
//    }
//}
//
//@Composable
//private fun SettingsPage(
//    modifier: Modifier = Modifier,
//    pageContent: @Composable (PaddingValues) -> Unit
//) {
//    Scaffold(
//        modifier = modifier,
//        topBar = { RortyToolbar(R.string.toolbar_settings_title) },
//        content = { pageContent.invoke(it) }
//    )
//}
//
//@Preview(showBackground = true, name = "Light Mode")
//@Preview(
//    showBackground = true, name = "Dark Mode",
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//)
//@Composable
//fun SettingsScreenPreview() {
//    JetRortyTheme {
//        SettingsPage {}
//    }
//}