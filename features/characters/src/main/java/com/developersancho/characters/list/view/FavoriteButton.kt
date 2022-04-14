package com.developersancho.characters.list.view

import android.content.res.Configuration
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.developersancho.characters.list.CharactersEvent
import com.developersancho.characters.list.CharactersViewModel
import com.developersancho.model.dto.character.CharacterDto
import com.developersancho.theme.Gray500
import com.developersancho.theme.JetRortyTheme
import com.developersancho.theme.Red

@Composable
fun FavoriteButton(
    viewModel: CharactersViewModel = hiltViewModel(),
    dto: CharacterDto
) {
    var isFavorite by rememberSaveable(dto) { mutableStateOf(dto.isFavorite) }

    IconButton(onClick = {
        isFavorite = !isFavorite
        dto.isFavorite = isFavorite
        viewModel.onTriggerEvent(CharactersEvent.AddOrRemoveFavorite(dto))
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
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "Light Mode"
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode"
)
@Composable
fun FavoriteButtonPreview() {
    JetRortyTheme {
        FavoriteButton(
            dto = CharacterDto.init()
        )
    }
}