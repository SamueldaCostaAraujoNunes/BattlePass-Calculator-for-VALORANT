@file:Suppress("UNREACHABLE_CODE")

package br.com.samuelnunes.valorantpassbattle.extensions.bindingAdapters

import android.animation.ValueAnimator
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import br.com.samuelnunes.valorantpassbattle.ui.view.adapter.FragmentSliderAdapter
import br.com.samuelnunes.valorantpassbattle.ui.view.adapter.ImageSliderAdapter
import br.com.samuelnunes.valorantpassbattle.ui.view.fragment.Rewards.BaseRewardsFragment
import br.com.samuelnunes.valorantpassbattle.ui.view.viewsCustom.CardModule
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import timber.log.Timber

@BindingAdapter("imagesURl", "indicator")
fun ViewPager2.imagesURl(imagesURl: List<String>?, indicator: DotsIndicator?) {
    if (imagesURl != null) {
        adapter = ImageSliderAdapter(imagesURl)
        indicator?.setViewPager2(this)
    }
}

fun ViewPager2.setAdapterSlider(mAdapter: FragmentSliderAdapter) {
    adapter = mAdapter
    registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            try {
                val item = mAdapter.getItem(position)
                val v: View? = item.view
                val viewParent = CardModule.parentIsCardmodule(v)
                if (viewParent is CardModule) {
                    viewParent.binding.titleName = item.toString()
                }
                if (item !is BaseRewardsFragment && v != null) {
                    changeDimensLayoutAnimation(v)
                }
            } catch (e: Exception) {
                Timber.e(e)
            }

        }

        private fun changeDimensLayoutAnimation(v: View) {
            v.post {
                val wMeasureSpec =
                    View.MeasureSpec.makeMeasureSpec(v.width, View.MeasureSpec.EXACTLY)
                val hMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                v.measure(wMeasureSpec, hMeasureSpec)
                try {
                    val anim = ValueAnimator.ofInt(layoutParams.height, v.measuredHeight + 30)
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
