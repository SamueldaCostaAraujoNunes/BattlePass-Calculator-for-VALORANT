package br.com.battlepassCalculatorValorant.model

import android.content.Context
import android.content.res.TypedArray
import android.util.TypedValue
import androidx.core.content.ContextCompat


class ColorFromXml(val context: Context) {

    fun getColorFromColors(colorId: Int): String {
        return "#" + Integer.toHexString(
            ContextCompat.getColor(
                context,
                colorId
            )
        ).substring(2)
    }

    private fun convertIntToHex(colorInt: Int): String {
        return String.format("#%06X", 0xFFFFFF and colorInt)
    }

    fun getColor(colorId: Int): String {
        val typedValue = TypedValue()
        val a: TypedArray =
            context.obtainStyledAttributes(typedValue.data, intArrayOf(colorId))
        val color = a.getColor(0, 0)
        a.recycle()
        return convertIntToHex(color)
    }

}