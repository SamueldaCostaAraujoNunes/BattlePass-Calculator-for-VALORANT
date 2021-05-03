package br.com.battlepassCalculatorValorant.extensions.bindingAdapters

import android.animation.ValueAnimator
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import br.com.battlepassCalculatorValorant.ui.view.adapter.MyPageSliderAdapter
import br.com.battlepassCalculatorValorant.ui.view.adapter.SliderImagesAdapter
import br.com.battlepassCalculatorValorant.ui.view.viewsCustom.CardModule
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

@BindingAdapter("imagesURl", "indicator")
fun ViewPager2.imagesURl(imagesURl: List<String>, indicator: DotsIndicator?) {
    adapter = SliderImagesAdapter(imagesURl)
    indicator?.setViewPager2(this)
}

fun ViewPager2.setAdapterSlider(mAdapter: MyPageSliderAdapter) {
    adapter = mAdapter
    registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)

            val item = mAdapter.getItem(position)
            val v: View = item.requireView()
            val viewParent = CardModule.parentIsCardmodule(v)
            if (viewParent is CardModule) {
                viewParent.binding.titleName = item.toString()
            }
            changeDimensLayoutAnimation(v)
        }

        private fun changeDimensLayoutAnimation(v: View) {
            v.post {
                val wMeasureSpec =
                    View.MeasureSpec.makeMeasureSpec(v.width, View.MeasureSpec.EXACTLY)
                val hMeasureSpec =
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                v.measure(wMeasureSpec, hMeasureSpec)
                try {
                    val anim = ValueAnimator.ofInt(layoutParams.height, v.measuredHeight)
                    anim.addUpdateListener {
                        val height = it.animatedValue as Int
                        layoutParams.height = height
                    }
                    anim.duration = 210
                    anim.start()
                } catch (e: IllegalStateException) {
                    layoutParams = (layoutParams as ViewGroup.LayoutParams)
                        .also { lp -> lp.height = v.measuredHeight }
                }
            }
        }

    })
}
