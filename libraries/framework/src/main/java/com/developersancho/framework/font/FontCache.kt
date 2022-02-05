package com.developersancho.framework.font

import android.content.Context
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat
import java.util.*

object FontCache {
    private val fontCache: Hashtable<Int, Typeface> = Hashtable<Int, Typeface>()
    operator fun get(font: Int, context: Context): Typeface? {
        if (fontCache.contains(font).not()) {
            try {
                fontCache[font] = ResourcesCompat.getFont(context, font)
            } catch (e: Exception) {
                return null
            }
        }
        return fontCache[font]
    }
}
