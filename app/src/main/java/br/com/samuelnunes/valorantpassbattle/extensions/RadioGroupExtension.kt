package br.com.samuelnunes.valorantpassbattle.extensions

import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.databinding.BindingAdapter

@BindingAdapter("checkChildrenByTag")
fun RadioGroup.checkChildrenByTag(tag: Int) {
    findViewWithTag<RadioButton>(tag).also { it.isChecked = true }
}

@BindingAdapter("checkedChange")
fun RadioGroup.checkedChange(function: (tag: Int) -> Unit) {
    setOnCheckedChangeListener { radioGroup, checkedId ->
        val radioButton = radioGroup.findViewById<RadioButton>(checkedId)
        val tag = radioButton.tag as Int
        function(tag)
    }
}
