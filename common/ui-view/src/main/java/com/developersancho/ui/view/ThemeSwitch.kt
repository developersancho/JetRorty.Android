package com.developersancho.ui.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.content.res.AppCompatResources
import com.developersancho.ui.resource.R
import com.google.android.material.switchmaterial.SwitchMaterial

class ThemeSwitch @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : SwitchMaterial(context, attrs) {

    init {
        thumbDrawable = AppCompatResources.getDrawable(context, R.drawable.selector_dark_light)
        trackDrawable = AppCompatResources.getDrawable(context, R.drawable.selector_bg_dark_light)
    }
}