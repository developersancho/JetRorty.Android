/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.ui.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.developersancho.ui.theme.JetRortyTheme
import com.developersancho.ui.theme.Red
import com.developersancho.ui.resource.R

@Suppress("ForbiddenComment")
@Composable
fun ErrorView(modifier: Modifier = Modifier, e: Throwable, action: () -> Unit) {
    // todo: handleThrowable- create extension method
    e.printStackTrace()
    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentHeight(Alignment.CenterVertically)
    ) {
        Icon(
            painter = rememberVectorPainter(Icons.Default.ErrorOutline),
            contentDescription = null,
            tint = Red,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        )
        SmallSpacer()
        Text(
            text = "${e.localizedMessage}",
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            textAlign = TextAlign.Center
        )
        SmallSpacer()
        Button(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            onClick = action
        ) {
            Text(text = stringResource(id = R.string.text_retry))
        }
    }
}

@Preview(
    showBackground = true,
    name = "Light Mode"
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode"
)
@Composable
fun ErrorPageViewPreview() {
    JetRortyTheme {
        ErrorView(e = Exception()) {}
    }
}