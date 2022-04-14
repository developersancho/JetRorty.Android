package com.developersancho.locations.detail.view

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.developersancho.component.DefaultSpacer
import com.developersancho.component.widget.JRDivider
import com.developersancho.jetframework.clickableSingle
import com.developersancho.locations.detail.LocationDetailState
import com.developersancho.model.dto.character.CharacterDto
import com.developersancho.model.dto.location.LocationDto
import com.developersancho.provider.NavigationProvider
import com.developersancho.theme.*
import com.developersancho.theme.R

@Composable
fun LocationDetailContent(data: LocationDetailState, navigator: NavigationProvider? = null) {
    LazyColumn {
        data.location?.let { dto ->
            item("locationInfo") {
                LocationDetailInfoView(dto)
            }

            if (dto.residentDtoList.isNotEmpty()) {
                item("locationContent") {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(id = R.string.text_characters),
                            modifier = Modifier
                                .padding(12.dp),
                            style = JetRortyTypography.h6
                        )
                        dto.residentDtoList.forEach {
                            LocationCharacterView(dto = it, navigator = navigator)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LocationDetailInfoView(dto: LocationDto) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.text_information),
            modifier = Modifier
                .padding(12.dp),
            style = JetRortyTypography.h6
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(JetRortyColors.cardBackgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                TextRow(
                    key = stringResource(id = R.string.text_name),
                    value = dto.name
                )
                JRDivider()
                DefaultSpacer()
                TextRow(
                    key = stringResource(id = R.string.text_dimension),
                    value = dto.dimension.toString()
                )
                JRDivider()
                DefaultSpacer()
                TextRow(
                    key = stringResource(id = R.string.text_type),
                    value = dto.type.toString()
                )
            }
        }
    }
}

@Composable
private fun TextRow(key: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = key,
            maxLines = 1,
            overflow = TextOverflow.Visible,
            style = JetRortyTypography.body2,
            textAlign = TextAlign.Start
        )
        Text(
            text = value,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = JetRortyTypography.subtitle1,
            textAlign = TextAlign.End
        )
    }
}

@Composable
private fun LocationCharacterView(dto: CharacterDto, navigator: NavigationProvider? = null) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(JetRortyColors.cardBackgroundColor)
            .clickableSingle {
                navigator?.openCharacterDetail(dto.id)
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
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(dto.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Text(
                    text = dto.name,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = JetRortyTypography.h6
                )
                Icon(
                    painter = rememberVectorPainter(Icons.Default.ArrowRight),
                    contentDescription = null,
                    tint = Gray400,
                    modifier = Modifier
                        .size(24.dp)
                )
            }
            DefaultSpacer()
            JRDivider()
        }
    }
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
fun LocationDetailScreenPreview() {
    JetRortyTheme {
        Surface(color = JetRortyColors.background) {
            LocationCharacterView(CharacterDto.init())
        }
    }
}