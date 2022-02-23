/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.provider

interface NavigationProvider {
    fun openDetail(id: Int)
    fun navigateUp()
}