package com.developersancho.detail.view

import android.content.res.Configuration
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.developersancho.detail.DetailViewState
import com.developersancho.model.dto.CharacterDto
import com.developersancho.model.dto.KeyValueModel
import com.developersancho.ui.theme.JetRortyTheme

@Composable
fun DetailPageContentView(data: DetailViewState) {
    LazyColumn {
        data.character?.let { character ->
            item("header") {
                DetailHeaderView(character = character)
            }

            item("content") {
                DetailContentView(list = data.list, character = character)
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
fun DetailPageContentViewPreview() {
    JetRortyTheme {
        DetailPageContentView(
            DetailViewState(
                character = CharacterDto.init(),
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
                )
            )
        )
    }
}