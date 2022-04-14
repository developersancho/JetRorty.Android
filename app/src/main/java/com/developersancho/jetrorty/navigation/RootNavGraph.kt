package com.developersancho.jetrorty.navigation

import com.developersancho.characters.detail.CharactersNavGraph
import com.developersancho.episodes.detail.EpisodesNavGraph
import com.developersancho.home.HomeNavGraph
import com.developersancho.locations.detail.LocationsNavGraph
import com.developersancho.settings.SettingsNavGraph
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec

object RootNavGraph : NavGraphSpec {
    override val route = "root"

    override val destinationsByRoute = emptyMap<String, DestinationSpec<*>>()

    override val startRoute = HomeNavGraph

    override val nestedNavGraphs = listOf(
        HomeNavGraph,
        CharactersNavGraph,
        EpisodesNavGraph,
        LocationsNavGraph,
        SettingsNavGraph
    )
}