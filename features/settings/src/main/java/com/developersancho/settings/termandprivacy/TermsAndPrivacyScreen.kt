package com.developersancho.settings.termandprivacy

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.developersancho.component.widget.JRToolbarWithNavIcon
import com.developersancho.jetframework.htmltext.HtmlText
import com.developersancho.provider.NavigationProvider
import com.developersancho.theme.JetRortyColors
import com.developersancho.theme.JetRortyTypography
import com.developersancho.theme.R
import com.developersancho.theme.cardBackgroundColor
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun TermsAndPrivacyScreen(
    navigator: NavigationProvider
) {
    Scaffold(
        topBar = {
            JRToolbarWithNavIcon(
                R.string.toolbar_terms_and_privacy_title,
                pressOnBack = {
                    navigator.navigateUp()
                }
            )
        },
        content = {
            Column(modifier = Modifier.background(JetRortyColors.cardBackgroundColor)) {
                HtmlText(
                    text = termsAndConditions.plus(privacyPolicy),
                    modifier = Modifier
                        .padding(12.dp)
                        .verticalScroll(rememberScrollState()),
                    style = JetRortyTypography.h4
                )
            }
        }
    )
}