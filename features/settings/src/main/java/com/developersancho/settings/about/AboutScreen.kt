package com.developersancho.settings.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.developersancho.component.ExtraSmallSpacer
import com.developersancho.component.MediumSpacer
import com.developersancho.component.SmallSpacer
import com.developersancho.component.widget.JRToolbarWithNavIcon
import com.developersancho.provider.NavigationProvider
import com.developersancho.theme.*
import com.developersancho.theme.R
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun AboutScreen(
    navigator: NavigationProvider
) {
    val uriHandler = LocalUriHandler.current
    val mediumLink = "https://medium.com/@developersancho"
    val githubLink = "https://github.com/developersancho"

    Scaffold(
        topBar = {
            JRToolbarWithNavIcon(
                R.string.toolbar_about_title,
                pressOnBack = {
                    navigator.navigateUp()
                }
            )
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    shape = JetRortyShapes.medium,
                    elevation = 8.dp
                ) {
                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                            .padding(12.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_profile),
                            contentDescription = null,
                            modifier = Modifier
                                .size(140.dp)
                                .clip(CircleShape)
                        )
                        MediumSpacer()
                        Text(
                            text = "Mr.Sanchez",
                            style = JetRortyTypography.h3,
                            textAlign = TextAlign.Center
                        )
                        ExtraSmallSpacer()
                        Text(
                            text = "Android Developer",
                            style = JetRortyTypography.h4,
                            textAlign = TextAlign.Center
                        )
                        ExtraSmallSpacer()
                        Text(
                            text = "Developed By @developersancho",
                            style = JetRortyTypography.h4,
                            textAlign = TextAlign.Center
                        )
                        SmallSpacer()
                        ClickableText(
                            text = AnnotatedString(text = githubLink),
                            style = JetRortyTypography.h6,
                            onClick = {
                                uriHandler.openUri(githubLink)
                            }
                        )
                        SmallSpacer()
                        ClickableText(
                            text = AnnotatedString(text = mediumLink),
                            style = JetRortyTypography.h6,
                            onClick = {
                                uriHandler.openUri(mediumLink)
                            }
                        )
                    }
                }
            }
        }
    )
}

@Preview(
    showBackground = true,
    name = "Light Mode"
)
@Composable
fun AboutScreenPreview() {
    JetRortyTheme {
        Surface(modifier = Modifier.background(JetRortyColors.background)) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                shape = JetRortyShapes.medium,
                elevation = 8.dp
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(12.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_profile),
                        contentDescription = null,
                        modifier = Modifier
                            .size(140.dp)
                            .clip(CircleShape)
                    )
                    MediumSpacer()
                    Text(
                        text = "Mr.Sanchez",
                        style = JetRortyTypography.h3,
                        textAlign = TextAlign.Center
                    )
                    ExtraSmallSpacer()
                    Text(
                        text = "Android Developer",
                        style = JetRortyTypography.h4,
                        textAlign = TextAlign.Center
                    )
                    ExtraSmallSpacer()
                    Text(
                        text = "Developed By @developersancho",
                        style = JetRortyTypography.h4,
                        textAlign = TextAlign.Center
                    )
                    SmallSpacer()
                    ClickableText(
                        text = AnnotatedString(text = "https://github.com/developersancho"),
                        style = JetRortyTypography.h6,
                        onClick = {
                        }
                    )
                    SmallSpacer()
                    ClickableText(
                        text = AnnotatedString(text = "https://medium.com/@developersancho"),
                        style = JetRortyTypography.h6,
                        onClick = {
                        }
                    )
                }
            }
        }
    }
}