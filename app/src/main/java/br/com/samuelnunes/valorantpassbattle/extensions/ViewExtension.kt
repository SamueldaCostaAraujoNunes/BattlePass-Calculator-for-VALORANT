package br.com.samuelnunes.valorantpassbattle.extensions.bindingAdapters

import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter
import br.com.samuelnunes.valorantpassbattle.extensions.getColorFromAttr

@BindingAdapter("visibilityIf")
fun View.visibilityIf(isVisible: Boolean = true) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

@ColorInt
fun View.getColor(@AttrRes resId: Int): Int {
    return context.getColorFromAttr(resId)
}