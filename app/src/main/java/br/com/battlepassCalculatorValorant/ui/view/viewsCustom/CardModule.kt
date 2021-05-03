package br.com.battlepassCalculatorValorant.ui.view.viewsCustom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.updatePadding
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.databinding.ViewCardBaseBinding
import br.com.battlepassCalculatorValorant.extensions.bindingAdapters.visibilityIf
import com.google.android.material.card.MaterialCardView

class CardModule @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : MaterialCardView(context, attrs, R.attr.mCardViewStyle) {

    private var linearLayout: LinearLayout = LinearLayout(context)
    var binding: ViewCardBaseBinding

    init {
        linearLayout.layoutParams = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        linearLayout.orientation = LinearLayout.VERTICAL

        this.addView(linearLayout)
        binding = ViewCardBaseBinding.inflate(
            LayoutInflater.from(context),
            linearLayout,
            true
        )
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.CardModule,
            0,
            0
        )

        val title = typedArray.getString(R.styleable.CardModule_title)
        val slider = typedArray.getBoolean(R.styleable.CardModule_isSlider, false)

        binding.titleName = title
        binding.isSlider = slider

        if (isInEditMode) {
            binding.title.let {
                it.text = title
                val scale = resources.displayMetrics.density
                val dpAsPixels = ((if (slider) 38 else 24) * scale + 0.5f)
                it.updatePadding(top = dpAsPixels.toInt())
                it.visibilityIf(title != null)
            }
        }
        typedArray.recycle()
    }

    override fun addView(child: View?) {
        if (child == linearLayout) {
            super.addView(child)
        } else {
            linearLayout.addView(child)
        }
    }

    override fun addView(child: View?, index: Int) {
        if (child == linearLayout) {
            super.addView(child, index)
        } else {
            linearLayout.addView(child, index)
        }
    }

    override fun addView(child: View?, params: ViewGroup.LayoutParams?) {
        if (child == linearLayout) {
            super.addView(child, params)
        } else {
            linearLayout.addView(child, params)
        }
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        if (child == linearLayout) {
            super.addView(child, index, params)
        } else {
            linearLayout.addView(child, index, params)
        }
    }

    override fun addView(child: View?, width: Int, height: Int) {
        if (child == linearLayout) {
            super.addView(child, width, height)
        } else {
            linearLayout.addView(child, width, height)
        }
    }

    override fun removeView(view: View?) {
        if (view == linearLayout) {
            super.removeView(view)
        } else {
            linearLayout.removeView(view)
        }
    }

    companion object {
        fun parentIsCardmodule(v: View?): CardModule? {
            val viewParent = v?.parent as View?
            return if (viewParent is CardModule?) {
                return viewParent
            } else {
                parentIsCardmodule(viewParent)
            }
        }
    }
}