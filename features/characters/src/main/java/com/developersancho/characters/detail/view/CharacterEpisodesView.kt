package com.developersancho.characters.detail.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.developersancho.component.ExtraSmallSpacer
import com.developersancho.component.widget.JRDivider
import com.developersancho.jetframework.clickableSingle
import com.developersancho.model.dto.episode.EpisodeDto
import com.developersancho.provider.NavigationProvider
import com.developersancho.theme.Gray400
import com.developersancho.theme.JetRortyColors
import com.developersancho.theme.JetRortyTypography
import com.developersancho.theme.cardBackgroundColor

@Composable
fun CharacterEpisodeView(dto: EpisodeDto, navigator: NavigationProvider? = null) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(JetRortyColors.cardBackgroundColor)
            .clickableSingle {
                navigator?.openEpisodeDetail(episodeId = dto.id)
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column {
                    Text(
                        text = dto.name,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = JetRortyTypography.h6
                    )
                    Text(text = dto.episode.toString(), style = JetRortyTypography.subtitle1)
                }
                Icon(
                    painter = rememberVectorPainter(Icons.Default.ArrowRight),
                    contentDescription = null,
                    tint = Gray400,
                    modifier = Modifier
                        .size(24.dp)
                )
            }
            ExtraSmallSpacer()
            JRDivider()
        }
    }
}