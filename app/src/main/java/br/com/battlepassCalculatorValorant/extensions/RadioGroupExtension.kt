package br.com.battlepassCalculatorValorant.extensions

import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.databinding.BindingAdapter

@BindingAdapter("checkChildrenByTag")
fun RadioGroup.checkChildrenByTag(tag: Int) {
    findViewWithTag<RadioButton>(tag).also { it.isChecked = true }
}

@BindingAdapter("checkedChange")
fun RadioGroup.checkedChange(function: (nextTheme: Int) -> Unit) {
    setOnCheckedChangeListener { radioGroup, checkedId ->
        val radioButton = radioGroup.findViewById<RadioButton>(checkedId)
        val nextTheme = radioButton.tag as Int
        function(nextTheme)
    }
}
