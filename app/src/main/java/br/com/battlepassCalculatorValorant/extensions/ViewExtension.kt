package br.com.battlepassCalculatorValorant.extensions.bindingAdapters

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("visibilityIf")
fun View.visibilityIf(isVisible: Boolean = true) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}