package com.developersancho.episodes.list.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.developersancho.model.dto.episode.EpisodeDto
import com.developersancho.theme.JetRortyColors
import com.developersancho.theme.JetRortyTheme
import com.developersancho.theme.JetRortyTypography
import com.developersancho.theme.RedDark

@Composable
fun EpisodeFavoriteRow(
    dto: EpisodeDto,
    onDetailClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {}
) {
    Card(
        onClick = onDetailClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(
                vertical = 4.dp,
                horizontal = 8.dp
            ),
        elevation = 8.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .padding(top = 12.dp, start = 8.dp, bottom = 4.dp)
            ) {
                Text(
                    text = dto.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 2.dp),
                    style = JetRortyTypography.subtitle1
                )
                Text(
                    text = dto.episode.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 2.dp),
                    style = JetRortyTypography.subtitle1
                )
                Text(
                    text = dto.airDate.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 2.dp),
                    style = JetRortyTypography.subtitle1
                )
            }

            IconButton(
                onClick = onDeleteClick,
                modifier = Modifier
                    .width(34.dp)
                    .height(34.dp)
            ) {
                Icon(
                    painter = rememberVectorPainter(Icons.Default.Delete),
                    contentDescription = null,
                    tint = RedDark
                )
            }
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
fun EpisodeFavoriteRowPreview() {
    JetRortyTheme {
        Surface(color = JetRortyColors.background) {
            EpisodeFavoriteRow(dto = EpisodeDto.init())
        }
    }
}