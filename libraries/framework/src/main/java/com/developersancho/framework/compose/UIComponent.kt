package com.developersancho.framework.compose

import androidx.compose.runtime.Composable

// All our components should implement this interface
interface UIComponent {
    @Composable
    fun contentView(): ComposableView
}