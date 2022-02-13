package com.developersancho.jetrorty.features.detail.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.developersancho.jetrorty.features.characters.view.CharacterStatusDotView
import com.developersancho.jetrorty.features.characters.view.CharacterStatusView
import com.developersancho.model.dto.CharacterDto
import com.developersancho.model.dto.KeyValueModel
import com.developersancho.ui.theme.JetRortyTheme
import com.developersancho.ui.view.RortyDivider

@Composable
fun DetailContentView(list: List<KeyValueModel>, character: CharacterDto) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                DetailCharacterStatusView(character = character)

                list.forEachIndexed { index, it ->
                    Column {
                        DetailTextRowView(it)

                        if (it != list.last()) {
                            RortyDivider()
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DetailTextRowView(model: KeyValueModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = model.key.toString(),
            maxLines = 1,
            overflow = TextOverflow.Visible,
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Start
        )
        Text(
            text = model.value.toString(),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.End
        )
    }

}

@Composable
private fun DetailCharacterStatusView(character: CharacterDto) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CharacterStatusDotView(character = character)
            Text(
                text = character.status?.value.toString(),
                maxLines = 1,
                overflow = TextOverflow.Visible,
                style = MaterialTheme.typography.body2
            )
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
fun DetailContentItemViewPreview() {
    JetRortyTheme {
        DetailContentView(
            list = listOf(
                KeyValueModel(
                    key = "Name",
                    value = "Mr. Sanchez"
                ),
                KeyValueModel(
                    key = "Species",
                    value = "Human"
                ),
                KeyValueModel(
                    key = "Gender",
                    value = "Male"
                ),
                KeyValueModel(
                    key = "Last Know Location",
                    value = "Alive Alive Alive Alive Alive Alive Alive Alive Alive Alive"
                ),
                KeyValueModel(
                    key = "Location",
                    value = "Istanbul"
                )
            ), CharacterDto.init()
        )
    }
}