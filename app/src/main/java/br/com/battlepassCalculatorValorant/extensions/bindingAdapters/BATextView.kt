package br.com.battlepassCalculatorValorant.extensions.bindingAdapters

import android.graphics.Color
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("adiantado")
fun TextView.adiantado(sucess: Boolean) {
    if (sucess) {
        setTextColor(Color.parseColor("#4CD964"))
    } else {
        setTextColor(Color.parseColor("#E69700"))
    }
}
