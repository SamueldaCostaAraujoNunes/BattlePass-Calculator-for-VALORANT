package br.com.samuelnunes.valorantpassbattle.ui.view.viewsCustom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import br.com.samuelnunes.valorantpassbattle.R
import br.com.samuelnunes.valorantpassbattle.databinding.ViewCardBaseBinding
import br.com.samuelnunes.valorantpassbattle.extensions.bindingAdapters.visibilityIf
import com.google.android.material.card.MaterialCardView
import timber.log.Timber

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

        binding.titleName = title

        if (isInEditMode) {
            binding.title.let {
                it.text = title
                it.visibilityIf(title != null)
            }
        }
        typedArray.recycle()
    }

    fun title(value: String) {
        binding.titleName = value
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
                viewParent
            } else {
                parentIsCardmodule(viewParent)
            }
        }

        fun ifParentIsCardModuleSetTitle(item: Fragment) {
            val v: View? = item.view
            val viewParent = parentIsCardmodule(v)
            if (viewParent is CardModule) {
                Timber.i("Entrou, item = $item")
                viewParent.binding.titleName = item.toString()
            }
        }
    }
}