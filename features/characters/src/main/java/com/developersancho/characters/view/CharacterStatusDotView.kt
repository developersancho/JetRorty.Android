package com.developersancho.characters.view

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.developersancho.model.dto.CharacterDto
import com.developersancho.model.remote.base.Status
import com.developersancho.ui.theme.JetRortyTheme

@Composable
fun CharacterStatusDotView(character: CharacterDto) {
    CharacterStatusDotContentView(
        isAlive = character.status == Status.Alive,
        isDead = character.status == Status.Dead
    )
}

@Composable
private fun CharacterStatusDotContentView(
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
fun CharacterStatusDotViewPreview() {
    JetRortyTheme {
        CharacterStatusDotView(CharacterDto.init())
    }
}