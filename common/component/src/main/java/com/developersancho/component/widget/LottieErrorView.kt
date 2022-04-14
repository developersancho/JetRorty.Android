package com.developersancho.component.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.developersancho.component.LottieView
import com.developersancho.component.SmallSpacer
import com.developersancho.theme.JetRortyTheme
import com.developersancho.theme.R

@Composable
fun LottieErrorView(modifier: Modifier = Modifier, e: Throwable, action: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentHeight(Alignment.CenterVertically)
    ) {
        LottieView(
            file = "error.json",
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        SmallSpacer()
        Text(
            text = e.localizedMessage,
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
fun LottieErrorViewPreview() {
    JetRortyTheme {
        LottieErrorView(e = Exception()) {}
    }
}