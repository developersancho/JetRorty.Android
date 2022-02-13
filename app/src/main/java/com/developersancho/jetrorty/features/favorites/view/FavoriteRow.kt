package com.developersancho.jetrorty.features.favorites.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.developersancho.jetrorty.features.characters.view.CharacterStatusDotView
import com.developersancho.model.dto.CharacterDto
import com.developersancho.ui.theme.JetRortyTheme
import com.developersancho.ui.theme.RedDark
import com.developersancho.ui.view.CoverImage

@Composable
fun FavoriteRow(
    modifier: Modifier = Modifier,
    dto: CharacterDto,
    onDetailClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {}
) {

    Card(
        onClick = onDetailClick,
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(
                vertical = 4.dp,
                horizontal = 8.dp
            ),
        elevation = 8.dp
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CoverImage(
                data = dto.image,
                imageModifier = modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(size = 4.dp)),
                elevation = 0.dp,
                backgroundColor = MaterialTheme.colors.surface
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = modifier
                    .weight(1f)
                    .fillMaxSize()
                    .padding(top = 12.dp, start = 4.dp, bottom = 4.dp)
            ) {
                Text(
                    text = dto.name.toString(),
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 2.dp),
                    style = MaterialTheme.typography.subtitle1
                )
                Text(
                    text = dto.species.toString(),
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 2.dp),
                    style = MaterialTheme.typography.subtitle1
                )
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        CharacterStatusDotView(character = dto)
                        Text(
                            text = dto.status?.value.toString(),
                            maxLines = 1,
                            overflow = TextOverflow.Visible,
                            style = MaterialTheme.typography.body2,
                            modifier = modifier
                        )
                    }
                }
            }

            IconButton(
                onClick = onDeleteClick,
                modifier = modifier
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

@Preview(showBackground = true, name = "Light Mode")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
fun FavoriteRowPreview() {
    JetRortyTheme {
        FavoriteRow(dto = CharacterDto.init())
    }
}