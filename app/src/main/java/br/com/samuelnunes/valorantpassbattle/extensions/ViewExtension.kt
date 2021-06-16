package br.com.samuelnunes.valorantpassbattle.extensions.bindingAdapters

import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter

@BindingAdapter("visibilityIf")
fun View.visibilityIf(isVisible: Boolean = true) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

@ColorInt
fun View.getColor(@AttrRes resId: Int): Int {
    val typedValue = TypedValue()
    val theme: Resources.Theme = context.theme
    theme.resolveAttribute(resId, typedValue, true)
    return typedValue.data
}