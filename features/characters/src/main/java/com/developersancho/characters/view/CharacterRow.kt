package com.developersancho.characters.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.developersancho.characters.CharactersViewModel
import com.developersancho.model.dto.CharacterDto
import com.developersancho.ui.theme.JetRortyTheme
import com.developersancho.ui.view.CoverImage
import org.koin.androidx.compose.getViewModel

@Composable
fun CharacterRow(
    viewModel: CharactersViewModel = getViewModel(),
    dto: CharacterDto,
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
        Row {
            CoverImage(
                data = dto.image,
                imageModifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(size = 4.dp)),
                elevation = 0.dp,
                backgroundColor = MaterialTheme.colors.surface
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 12.dp, start = 4.dp, bottom = 4.dp)
            ) {
                Text(
                    text = dto.name.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 2.dp),
                    style = MaterialTheme.typography.subtitle1
                )
                Text(
                    text = dto.species.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 2.dp),
                    style = MaterialTheme.typography.subtitle1
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    CharacterStatusView(dto)

                    FavoriteButton(viewModel, dto)
                }
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
fun CharacterRowPreview() {
    JetRortyTheme {
        CharacterRow(dto = CharacterDto.init())
    }
}