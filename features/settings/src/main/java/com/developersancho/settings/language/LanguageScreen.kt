package com.developersancho.settings.language

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.developersancho.component.widget.JRToolbarWithNavIcon
import com.developersancho.jetframework.SetLanguage
import com.developersancho.jetframework.clickableSingle
import com.developersancho.provider.NavigationProvider
import com.developersancho.theme.*
import com.developersancho.theme.R
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.launch

@Destination
@Composable
fun LanguageScreen(
    viewModel: LanguageViewModel = hiltViewModel(),
    navigator: NavigationProvider
) {
    var langs by remember { viewModel.langs }
    val scope = rememberCoroutineScope()

    SetLanguage(langs.find { it.isSelected }?.code.toString())

    Scaffold(
        topBar = {
            JRToolbarWithNavIcon(
                R.string.toolbar_app_language_title,
                pressOnBack = {
                    navigator.navigateUp()
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize()
        ) {
            items(items = langs) { lang ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(JetRortyColors.cardBackgroundColor)
                        .clickableSingle {
                            scope.launch {
                                viewModel.saveLanguageCode(lang.code)
                            }
                            langs = langs.map { dto ->
                                if (lang.id == dto.id) {
                                    dto.copy(isSelected = true)
                                } else {
                                    dto.copy(isSelected = false)
                                }
                            }
                        }
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = lang.name, style = JetRortyTypography.subtitle1)
                    AnimatedVisibility(lang.isSelected) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Selected",
                            tint = Red700,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}