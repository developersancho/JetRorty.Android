package com.developersancho.locations.list.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.developersancho.locations.list.LocationsEvent
import com.developersancho.locations.list.LocationsViewModel
import com.developersancho.model.dto.location.LocationDto
import com.developersancho.theme.*

@Composable
fun LocationRow(
    viewModel: LocationsViewModel = hiltViewModel(),
    dto: LocationDto,
    onDetailClick: () -> Unit = {}
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
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
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
                text = dto.dimension.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 2.dp),
                style = JetRortyTypography.subtitle1
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = dto.type.toString(),
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 2.dp),
                    style = JetRortyTypography.subtitle1
                )

                FavoriteButton(viewModel, dto)
            }
        }
    }
}

@Composable
private fun FavoriteButton(
    viewModel: LocationsViewModel = hiltViewModel(),
    dto: LocationDto
) {
    var isFavorite by rememberSaveable(dto) { mutableStateOf(dto.isFavorite) }

    IconButton(onClick = {
        isFavorite = !isFavorite
        dto.isFavorite = isFavorite
        viewModel.onTriggerEvent(LocationsEvent.AddOrRemoveFavorite(dto))
    }) {
        val tintColor = if (isFavorite) Red else Gray500
        Icon(
            painter = rememberVectorPainter(Icons.Default.Favorite),
            contentDescription = null,
            tint = tintColor
        )
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
fun ELocationRowPreview() {
    JetRortyTheme {
        Surface(color = JetRortyColors.background) {
            LocationRow(dto = LocationDto.init())
        }
    }
}