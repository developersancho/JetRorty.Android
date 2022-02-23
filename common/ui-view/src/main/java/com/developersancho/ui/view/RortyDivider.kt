/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.ui.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.developersancho.ui.theme.DividerDark
import com.developersancho.ui.theme.DividerLight
import com.developersancho.ui.theme.JetRortyTheme

@Composable
fun RortyDivider(modifier: Modifier = Modifier) {
    val isLightTheme = MaterialTheme.colors.isLight
    val dividerColor =
        if (!isLightTheme) DividerDark else DividerLight
    Divider(
        modifier = modifier
            .fillMaxWidth()
            .height(1.dp),
        color = dividerColor
    )
}

@Preview("default", showBackground = true)
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun DividerPreview() {
    JetRortyTheme {
        Box(Modifier.size(height = 10.dp, width = 100.dp)) {
            RortyDivider(Modifier.align(Alignment.Center))
        }
    }
}