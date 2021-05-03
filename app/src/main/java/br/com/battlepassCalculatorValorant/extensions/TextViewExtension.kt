package br.com.battlepassCalculatorValorant.extensions.bindingAdapters

import android.graphics.Color
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
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

@BindingAdapter("fadeAnimationText")
fun TextView.fadeAnimationText(newText: String?) {
    if (newText != null) {
        val anim = AlphaAnimation(1.0f, 0.0f)
        anim.duration = 200
        anim.repeatCount = 1
        anim.repeatMode = Animation.REVERSE

        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(animation: Animation?) {}
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationRepeat(animation: Animation?) {
                text = newText
            }
        })
        startAnimation(anim)
    } else {
        text = newText
    }

}