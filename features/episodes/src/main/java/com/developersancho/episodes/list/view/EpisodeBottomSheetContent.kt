package com.developersancho.episodes.list.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developersancho.model.dto.episode.EpisodeDto
import com.developersancho.theme.*
import com.developersancho.theme.R

@Composable
fun EpisodeBottomSheetContent(
    episode: EpisodeDto,
    onApprove: () -> Unit = {},
    onCancel: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(JetRortyColors.primary)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = stringResource(id = R.string.text_favor_delete),
                    modifier = Modifier.padding(start = 16.dp),
                    fontWeight = FontWeight.Bold,
                    color = Red,
                    fontSize = 18.sp
                )
                Text(
                    text = episode.name,
                    modifier = Modifier.padding(start = 16.dp),
                    fontWeight = FontWeight.SemiBold
                )
            }

            TextButton(
                onClick = { onCancel() },
                modifier = Modifier.padding(end = 8.dp),
                contentPadding = PaddingValues(
                    start = 6.dp,
                    top = 0.dp,
                    end = 10.dp,
                    bottom = 0.dp
                ),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_close_circle),
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize),
                    tint = Red700
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(
                    text = stringResource(id = R.string.text_cancel),
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.Normal
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )

        Text(
            text = stringResource(
                id = R.string.text_delete_favor_description,
                episode.name
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            fontWeight = FontWeight.Normal
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )

        Button(
            onClick = {
                onApprove.invoke()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Red),
            shape = RectangleShape
        ) {
            Text(
                text = stringResource(id = R.string.text_approve_remove),
                color = White,
                modifier = Modifier.padding(4.dp)
            )
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )
    }
}