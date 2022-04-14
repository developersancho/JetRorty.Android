package com.developersancho.characters.detail.view

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.developersancho.component.widget.JRDivider
import com.developersancho.model.dto.character.CharacterDto
import com.developersancho.model.remote.base.Status
import com.developersancho.theme.*
import com.developersancho.theme.R

@Composable
fun CharacterDetailInfoView(character: CharacterDto) {
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
                CharacterDetailStatusView(character = character)

                TextRow(
                    key = stringResource(id = R.string.text_name),
                    value = character.name
                )

                JRDivider()

                TextRow(
                    key = stringResource(id = R.string.text_species),
                    value = character.species.toString()
                )

                JRDivider()

                TextRow(
                    key = stringResource(id = R.string.text_gender),
                    value = character.gender.toString()
                )
            }
        }
    }
}

@Composable
private fun CharacterDetailStatusView(character: CharacterDto) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CharacterDetailStatusDotView(character = character)
            Text(
                text = character.status.value,
                maxLines = 1,
                overflow = TextOverflow.Visible,
                style = JetRortyTypography.body2
            )
        }
    }
}

@Composable
private fun CharacterDetailStatusDotView(character: CharacterDto) {
    CharacterDetailStatusDotContentView(
        isAlive = character.status == Status.Alive,
        isDead = character.status == Status.Dead
    )
}

@Composable
private fun CharacterDetailStatusDotContentView(
    isAlive: Boolean,
    isDead: Boolean
) {
    Spacer(
        Modifier
            .size(12.dp)
            .clip(CircleShape)
            .background(
                when {
                    isAlive -> Color.Green
                    isDead -> Color.Red
                    else -> Color.Gray
                }
            )
    )
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
fun CharacterDetailInfoViewPreview() {
    JetRortyTheme {
        Surface(color = JetRortyColors.background) {
            CharacterDetailInfoView(
                CharacterDto.init()
            )
        }
    }
}