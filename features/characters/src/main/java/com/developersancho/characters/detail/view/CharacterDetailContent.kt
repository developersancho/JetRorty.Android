package com.developersancho.characters.detail.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.developersancho.characters.detail.CharacterDetailViewState
import com.developersancho.provider.NavigationProvider
import com.developersancho.theme.JetRortyTypography
import com.developersancho.theme.R

@Composable
fun CharacterDetailContent(data: CharacterDetailViewState, navigator: NavigationProvider? = null) {
    LazyColumn {
        data.character?.let { character ->
            item("header") {
                CharacterDetailHeaderView(character = character)
            }

            item("contentInfo") {
                CharacterDetailInfoView(character = character)
            }

            item("contentLocation") {
                CharacterDetailLocationView(character = character, navigator = navigator)
            }

            if (character.episodeDtoList.isNotEmpty()) {
                item("contentEpisode") {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(id = R.string.text_episodes),
                            modifier = Modifier
                                .padding(12.dp),
                            style = JetRortyTypography.h6
                        )
                        character.episodeDtoList.forEach {
                            CharacterEpisodeView(dto = it, navigator = navigator)
                        }
                    }
                }
            }
        }
    }
}