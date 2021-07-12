package br.com.samuelnunes.valorantpassbattle.extensions.bindingAdapters

import android.graphics.Paint
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.TextView
import androidx.databinding.BindingAdapter
import br.com.samuelnunes.valorantpassbattle.R
import br.com.samuelnunes.valorantpassbattle.extensions.formatterLocale
import br.com.samuelnunes.valorantpassbattle.extensions.getColorFromAttr
import timber.log.Timber
import java.time.LocalDate
import java.time.format.FormatStyle
import java.util.*
import kotlin.concurrent.schedule


@BindingAdapter("adiantado")
fun TextView.adiantado(sucess: Boolean) {
    if (sucess) {
        setTextColor(context.getColorFromAttr(R.attr.colorAccent))
    } else {
        setTextColor(context.getColorFromAttr(R.attr.colorError))
    }
}

@BindingAdapter(value = ["fadeAnimationText", "orderAnimation"], requireAll = false)
fun TextView.fadeAnimationText(newText: String?, orderAnimation: Int = 0) {
    if (newText != null) {
        val anim = AlphaAnimation(1.0f, 0.0f)
        anim.duration = 275
        anim.repeatCount = 1
        anim.repeatMode = Animation.REVERSE

        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(animation: Animation?) {}
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationRepeat(animation: Animation?) {
                text = newText
            }
        })
        try {
            Timer().schedule(orderAnimation.toLong() * 150) {
                startAnimation(anim)
            }
        } catch (e: Exception) {
            Timber.e(e)
            startAnimation(anim)
        }


    } else {
        text = newText
    }
}

@BindingAdapter(value = ["localDate", "localDateFormat"], requireAll = false)
fun TextView.localDate(localDate: LocalDate?, formatStyle: FormatStyle? = FormatStyle.MEDIUM) {
    if (localDate != null && formatStyle != null) {
        fadeAnimationText(localDate.formatterLocale(context, formatStyle))
    }
}

@BindingAdapter("position", "positionCurrent")
fun TextView.comparableStyle(position: Int?, positionCurrent: Int?) {
    if (position != null && positionCurrent != null) {
        val gray = getColor(R.attr.colorOnSecondary)
        val white = getColor(R.attr.colorOnPrimary)
        val accent = getColor(R.attr.colorAccent)

        setTextColor(white)
        paintFlags = paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        if (position < positionCurrent) {
            paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            setTextColor(gray)
        } else if (position == positionCurrent) {
            setTextColor(accent)
        }
    }
}
