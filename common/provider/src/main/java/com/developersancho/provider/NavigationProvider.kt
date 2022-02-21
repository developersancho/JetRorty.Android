package com.developersancho.provider

interface NavigationProvider {
    fun openDetail(id: Int)
    fun navigateUp()
}