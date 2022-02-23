/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.main

import androidx.navigation.NavController
import com.developersancho.detail.destinations.DetailPageDestination
import com.developersancho.provider.NavigationProvider
import com.ramcosta.composedestinations.navigation.navigateTo

class CommonNavigationProvider(
    private val navController: NavController
) : NavigationProvider {

    override fun openDetail(id: Int) {
        navController.navigateTo(DetailPageDestination(id))
    }

    override fun navigateUp() {
        navController.navigateUp()
    }
}